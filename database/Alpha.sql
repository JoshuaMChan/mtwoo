create database if not exists `mtwoo`;
set names utf8;

use `mtwoo`;

drop table if exists `user`;

create table `user` (
    `id` int(255) not null auto_increment,
    `email` char(64) not null,
    `password` char(32) not null,
    primary key (`id`)
)

# create table `post`(
#     `id` int(255) not null auto_increment,
#
# )