Hướng dẫn cài đặt code 
tải code 
tạo db trong mysql
cập nhập lại application.properties
sửa lại db mới thêm 
thêm "# Login with Google
spring.security.oauth2.client.registration.google.client-id={client-id-cua-ban}
spring.security.oauth2.client.registration.google.client-secret={client-secret-cua-ban}
spring.security.oauth2.client.registration.google.scope=profile,email
spring.security.oauth2.client.registration.google.redirect-uri={baseUrl}/login/oauth2/code/google
spring.security.oauth2.client.provider.google.authorization-uri=https://accounts.google.com/o/oauth2/auth
spring.security.oauth2.client.provider.google.token-uri=https://oauth2.googleapis.com/token
spring.security.oauth2.client.provider.google.user-info-uri=https://www.googleapis.com/oauth2/v3/userinfo
spring.security.oauth2.client.provider.google.user-name-attribute=sub

"
chạy code vào db cập nhật bảng role "1 ADMIN, 2 USER"
tạo tài khoản xong set roles cho tài khoản và có thể đăng nhập được
thêm producttype các dữ liệu sau "Nam, Nữ, Trẻ em"
 
 
