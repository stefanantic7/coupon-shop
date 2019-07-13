# CouponShop

CouponShop is admin panel application for coupons that represent the discounted price for articles.

Application is based on RESTful API.
[CouponShop frontend](https://github.com/stefanantic7/coupon-shop-front) 

Authentication is based on JWT generated by the server.


## Install dependencies
```
mvn clean install 
```

After that you should put your dependencies into web/lib folder.

## Run migrations
First you should setup your database credentials in pom.xml file.
```
<configuration>
    <driver>com.mysql.cj.jdbc.Driver</driver>
    <url>jdbc:mysql://192.168.10.10:3306?autoreconnect=true</url>
    <user>homestead</user>
    <password>secret</password>
    <schemas>
        <schema>couponshop</schema>
    </schemas>
    <encoding>ISO-8859-1</encoding>
    <cleanOnValidationError>true</cleanOnValidationError>
</configuration>
```
and run:
```
mvn flyway:migrate
```


