# Hướng Dẫn Git Commands (Từ SourceTree sang Command Line)

## 📋 Mục Lục
1. [Xem Trạng Thái & Thông Tin](#xem-trạng-thái--thông-tin)
2. [Thêm File vào Staging](#thêm-file-vào-staging)
3. [Commit](#commit)
4. [Push & Pull](#push--pull)
5. [Branch](#branch)
6. [Undo & Revert](#undo--revert)
7. [Xem Lịch Sử](#xem-lịch-sử)

---

## 🔍 Xem Trạng Thái & Thông Tin

### Xem trạng thái repo (giống SourceTree: Working Directory)
```bash
git status
```
- **SourceTree tương đương**: Nhìn vào "Working Directory" để thấy file nào đã thay đổi
- **Kết quả**: Hiển thị file đã thay đổi, file mới, file đã xóa

### Xem chi tiết thay đổi
```bash
git diff
```
- **SourceTree tương đương**: Click vào file để xem diff
- **Kết quả**: Hiển thị từng dòng thay đổi

### Xem thay đổi của file cụ thể
```bash
git diff tên-file
# Ví dụ: git diff src/main/java/com/techlab/renderpdf/controller/PdfController.java
```

### Xem thay đổi đã staged (sắp commit)
```bash
git diff --staged
# hoặc
git diff --cached
```

---

## ➕ Thêm File vào Staging

### Thêm file cụ thể
```bash
git add tên-file
# Ví dụ: git add src/main/java/com/techlab/renderpdf/controller/PdfController.java
```
- **SourceTree tương đương**: Checkbox bên cạnh file trong "Unstaged files"

### Thêm tất cả file đã thay đổi
```bash
git add .
```
- **SourceTree tương đương**: Nút "Stage All"

### Thêm file theo pattern
```bash
git add *.java          # Thêm tất cả file .java
git add src/**/*.java   # Thêm tất cả file .java trong thư mục src
```

### Xóa file khỏi staging (unstage)
```bash
git reset tên-file
# hoặc
git restore --staged tên-file
```
- **SourceTree tương đương**: Uncheck file trong "Staged files"

### Xóa TẤT CẢ khỏi staging
```bash
git reset
```

---

## ✅ Commit

### Commit với message
```bash
git commit -m "Nội dung commit message"
# Ví dụ: git commit -m "Fix bug trong PdfController"
```
- **SourceTree tương đương**: Nhập message ở dưới và click "Commit"

### Commit tất cả file đã thay đổi (bỏ qua staging)
```bash
git commit -a -m "Nội dung commit message"
# hoặc
git commit -am "Nội dung commit message"
```
- **Lưu ý**: Chỉ commit file đã tracked (đã từng được git quản lý), file mới vẫn cần `git add` trước

### Commit với message dài (mở editor)
```bash
git commit
```
- Sẽ mở editor (vim/nano) để nhập message dài hơn

### Xem commit vừa tạo
```bash
git log -1
```

---

## 📤 Push & Pull

### Push lên remote (sau khi commit)
```bash
git push
```
- **SourceTree tương đương**: Nút "Push" (mũi tên lên)
- **Lần đầu push branch mới**: `git push -u origin tên-branch` hoặc `git push --set-upstream origin tên-branch`

### Pull từ remote (lấy code mới nhất)
```bash
git pull
```
- **SourceTree tương đương**: Nút "Pull" (mũi tên xuống)
- **Tương đương**: `git fetch` + `git merge`

### Fetch (chỉ lấy thông tin, không merge)
```bash
git fetch
```
- **SourceTree tương đương**: Nút "Fetch"
- Lấy thông tin về branch mới nhưng không merge vào code local

### Pull với rebase (giữ lịch sử sạch hơn)
```bash
git pull --rebase
```

---

## 🌿 Branch

### Xem tất cả branch
```bash
git branch              # Branch local
git branch -a           # Tất cả branch (local + remote)
git branch -r           # Chỉ branch remote
```
- **SourceTree tương đương**: Cột bên trái hiển thị branch

### Tạo branch mới
```bash
git branch tên-branch
# Ví dụ: git branch feature/add-new-template
```

### Chuyển sang branch khác
```bash
git checkout tên-branch
# Ví dụ: git checkout feature/add-new-template
```

### Tạo và chuyển sang branch mới (một lệnh)
```bash
git checkout -b tên-branch
# hoặc (Git 2.23+)
git switch -c tên-branch
```

### Xóa branch local
```bash
git branch -d tên-branch      # Xóa branch đã merge
git branch -D tên-branch      # Xóa branch chưa merge (force)
```

### Push branch mới lên remote
```bash
git push -u origin tên-branch
```

### Xóa branch trên remote
```bash
git push origin --delete tên-branch
```

### Merge branch vào branch hiện tại
```bash
git merge tên-branch
# Ví dụ: Đang ở main, muốn merge feature vào: git merge feature
```
- **SourceTree tương đương**: Click phải branch → Merge vào...

---

## ↩️ Undo & Revert

### Hoàn tác file về trạng thái trước khi sửa (chưa commit)
```bash
git restore tên-file
# hoặc (Git cũ hơn)
git checkout -- tên-file
```
- **SourceTree tương đương**: Discard file

### Hoàn tác TẤT CẢ file chưa commit
```bash
git restore .
# hoặc
git checkout -- .
```
- **⚠️ CẢNH BÁO**: Mất tất cả thay đổi chưa commit!

### Sửa commit message vừa tạo (chưa push)
```bash
git commit --amend -m "Message mới"
```

### Thêm file vào commit vừa tạo (chưa push)
```bash
git add tên-file
git commit --amend --no-edit
```

### Undo commit cuối cùng (giữ thay đổi trong working directory)
```bash
git reset --soft HEAD~1
```

### Undo commit và unstage (giữ file thay đổi nhưng chưa staged)
```bash
git reset HEAD~1
# hoặc
git reset --mixed HEAD~1
```

### Undo commit và xóa thay đổi (mất hết)
```bash
git reset --hard HEAD~1
```
- **⚠️ CẢNH BÁO**: Mất tất cả thay đổi!

### Revert commit (tạo commit mới để undo)
```bash
git revert HEAD              # Revert commit cuối
git revert commit-hash       # Revert commit cụ thể
```
- **Khác với reset**: Revert tạo commit mới, an toàn hơn khi đã push

---

## 📜 Xem Lịch Sử

### Xem log (lịch sử commit)
```bash
git log
```

### Log ngắn gọn (1 dòng mỗi commit)
```bash
git log --oneline
```

### Log với graph (xem branch)
```bash
git log --oneline --graph --all
```

### Log của file cụ thể
```bash
git log tên-file
```

### Xem thay đổi trong commit cụ thể
```bash
git show commit-hash
# hoặc
git show HEAD              # Commit cuối cùng
```

### Xem ai sửa dòng nào (blame)
```bash
git blame tên-file
```

---

## 🔧 Các Lệnh Hữu Ích Khác

### Xem cấu hình git
```bash
git config --list              # Tất cả config
git config user.name           # User name
git config user.email          # User email
git config --local --list      # Config của repo này
```

### Clone repo
```bash
git clone url-repo
# Ví dụ: git clone https://github.com/mrliz68-dev/convert-docx-to-pdf.git
```

### Xem remote
```bash
git remote -v                  # Xem tất cả remote
git remote show origin         # Chi tiết remote origin
```

### Stash (tạm thời lưu thay đổi)
```bash
git stash                      # Lưu thay đổi
git stash list                 # Xem danh sách stash
git stash pop                  # Lấy lại thay đổi
git stash apply                # Apply stash nhưng giữ stash
```
- **SourceTree tương đương**: Nút "Stash"

### Xem file trong commit khác
```bash
git show commit-hash:tên-file
```

---

## 📝 Workflow Thông Dụng

### Workflow cơ bản (mỗi ngày)
```bash
# 1. Xem trạng thái
git status

# 2. Xem thay đổi
git diff

# 3. Thêm file vào staging
git add .

# 4. Commit
git commit -m "Mô tả thay đổi"

# 5. Push
git push
```

### Workflow với branch mới
```bash
# 1. Tạo và chuyển sang branch mới
git checkout -b feature/new-feature

# 2. Làm việc, commit
git add .
git commit -m "Add new feature"

# 3. Push branch lên remote
git push -u origin feature/new-feature

# 4. Sau khi làm xong, merge vào main
git checkout main
git pull
git merge feature/new-feature
git push
```

### Workflow khi có conflict
```bash
# 1. Pull code mới
git pull

# 2. Nếu có conflict, sửa file conflict
# 3. Sau khi sửa xong
git add .
git commit -m "Resolve conflict"
git push
```

---

## 🆘 Xử Lý Tình Huống Thường Gặp

### Quên thêm file vào commit
```bash
git add tên-file
git commit --amend --no-edit
```

### Commit nhầm message
```bash
git commit --amend -m "Message đúng"
```

### Push nhầm lên branch khác
```bash
# Xóa commit trên remote
git push origin --delete tên-branch
# Hoặc force push (cẩn thận!)
git push --force
```

### Lấy code từ branch khác
```bash
git checkout tên-branch -- tên-file
```

---

## 💡 Tips

1. **Luôn kiểm tra status trước khi commit**: `git status`
2. **Commit message rõ ràng**: Mô tả ngắn gọn những gì đã làm
3. **Commit thường xuyên**: Không để quá nhiều thay đổi trong 1 commit
4. **Pull trước khi push**: Đảm bảo code local đã update
5. **Dùng `git log --oneline --graph`**: Để hiểu rõ hơn về lịch sử
6. **Cẩn thận với `git reset --hard`**: Có thể mất code!

---

## 📚 Tham Khảo Thêm

- [Git Documentation](https://git-scm.com/doc)
- [Git Cheat Sheet](https://education.github.com/git-cheat-sheet-education.pdf)

---

**Lưu ý**: File này chỉ là hướng dẫn cơ bản. Thực hành nhiều sẽ giúp bạn quen hơn với git commands! 🚀

