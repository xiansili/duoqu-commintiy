#多趣社区

## 建表语句
```
create table USER
  (
      ID           int auto_increment
          primary key,
      ACCOUNT_ID   varchar(100) null,
      NAME         varchar(50)  null,
      TOKEN        varchar(36)  null,
      GMT_CREATE   bigint       null,
      GMT_MODIFIED bigint       null,
      AVATAR_URL   varchar(100) not null
  );
  
  create table question
  (
      id            int           not null
          primary key,
      title         varchar(50)   not null,
      description   text          not null,
      gmt_create    bigint        not null,
      gmt_modified  bigint        not null,
      creator       varchar(30)   not null,
      comment_count int default 0 null,
      view_count    int default 0 null,
      like_count    int default 0 null,
      tags          varchar(256)  null
);
```

##提交记录
***
**1.八月23日**
1.publish 功能   



