FROM openjdk:8
#-> Mô trường được chạy

WORKDIR /app
#-> Đặt các thư mục làm việc của docker tại đây

COPY . /app/
#-> Sao chép các thư mục làm việc từ dự dán vào đây

RUN javac Main.java
#-> Tìm kiếm file Main để chạy chương trình

ENTRYPOINT ["java", "Main"]
#-> Khi container chạy thì lệnh 'java Main' sẽ thực thi. 'java' là trình thực thì và 'Main' là tên lớp chứa hàm chạy chương trình
