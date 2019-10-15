## Learning case1

## Reference:
[Spring文档](https://spring.io/guides/)
[Spring Web文档](https://spring.io/guides/gs/serving-web-content/)
[Bootstrap文档](https://v3.bootcss.com/getting-started/)
[Github OAuth](https://developer.github.com/apps/building-oauth-apps/creating-an-oauth-app/)
[Spring Embedded database](https://docs.spring.io/spring-boot/docs/2.2.0.RC1/reference/html/spring-boot-features.html#boot-features-embedded-database-support)

## 工具
[Git](https://git-scm.com/download)
[Visual Paradigm](https://www.visual-paradigm.com)
[Git工具使用教程](https://www.bilibili.com/video/av55780016/?p=2)

## scripts
~~~
-- auto-generated definition
create table USER
(
    ID           INTEGER default auto_increment,
    ACCOUNT_ID   VARCHAR(100),
    NAME         VARCHAR(50),
    TOKEN        CHAR(36),
    GMT_CREATE   BIGINT,
    GMT_MODIFIED BIGINT,
    constraint USER_PK
        primary key (ID)
);


~~~
