create database if not exists `mtwoo`;
set names utf8;

use `mtwoo`;

drop table if exists `user`;
create table `user` (
    `id` int(255) not null auto_increment,
    `email` char(64) not null,
    `password` char(32) not null,
    primary key (`id`)
);

drop table if exists `post`;
create table `post`(
    `id` int(255) not null auto_increment,
    `title` char(64) not null,
    `context` text not null,
    `user_id` int(255) not null,
    `date` datetime DEFAULT NULL,
    primary key (`id`),
    foreign key (`user_id`) references `user`(`id`)
);

# drop table if exists `tag`;
# create table `tag`(
#     `id` int(255) not null auto_increment,
#     `name` char(32) not null,
#     primary key (`id`)
# );
#
# drop table if exists `reply`;
# create table `reply`(
#     `id` int(255) not null auto_increment,
#     `context` text not null,
#     `date` datetime DEFAULT NULL,
#     primary key (`id`)
# );
#
# drop table if exists `group`;
# create table `group`(
#     `id` int(255) not null auto_increment,
#     `title` text not null,
#     primary key (`id`)
# );

