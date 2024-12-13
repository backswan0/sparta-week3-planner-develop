create table plans
(
    id         bigint auto_increment comment '일정 식별자'
        primary key,
    username   varchar(50) not null comment '작성자 이름',
    title      varchar(50) not null comment '일정 제목',
    task       varchar(100) null comment '일정 내용',
    created_at datetime(6)  not null comment '일정 생성일',
    updated_at datetime(6)  not null comment '일정 수정일'
);