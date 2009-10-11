
    alter table FriendRequest 
        drop 
        foreign key fk_FriendRequest_user_id_User_id;

    alter table FriendRequest 
        drop 
        foreign key fk_FriendRequest_friend_id_User_id;

    alter table Profile 
        drop 
        foreign key fk_Profile_user_id_User_id;

    alter table Scrap 
        drop 
        foreign key fk_Scrap_receiver_id_User_id;

    alter table Scrap 
        drop 
        foreign key fk_Scrap_author_id_User_id;

    alter table friend_reference 
        drop 
        foreign key fk_Friend_Reference_user_id_User_id;

    alter table friend_reference 
        drop 
        foreign key fk_Friend_Reference_friend_id_User_id;

    drop table if exists FriendRequest;

    drop table if exists Profile;

    drop table if exists Scrap;

    drop table if exists User;

    drop table if exists friend_reference;

    create table FriendRequest (
        id bigint not null auto_increment,
        message text,
        friend_id bigint not null,
        user_id bigint not null,
        primary key (id)
    );

    create table Profile (
        user_id bigint not null,
        firstName varchar(255),
        lastName varchar(255),
        email varchar(255),
        image mediumblob,
        thumb mediumblob,
        primary key (user_id)
    );

    create table Scrap (
        id bigint not null auto_increment,
        message text not null,
        time datetime not null,
        author_id bigint not null,
        receiver_id bigint not null,
        primary key (id)
    );

    create table User (
        id bigint not null auto_increment,
        userName varchar(255) not null unique,
        password varchar(255) not null,
        primary key (id)
    );

    create table friend_reference (
        friend_id bigint not null,
        user_id bigint not null,
        primary key (friend_id, user_id)
    );

    alter table FriendRequest 
        add index fk_FriendRequest_user_id_User_id (user_id), 
        add constraint fk_FriendRequest_user_id_User_id 
        foreign key (user_id) 
        references User (id);

    alter table FriendRequest 
        add index fk_FriendRequest_friend_id_User_id (friend_id), 
        add constraint fk_FriendRequest_friend_id_User_id 
        foreign key (friend_id) 
        references User (id);

    alter table Profile 
        add index fk_Profile_user_id_User_id (user_id), 
        add constraint fk_Profile_user_id_User_id 
        foreign key (user_id) 
        references User (id);

    alter table Scrap 
        add index fk_Scrap_receiver_id_User_id (receiver_id), 
        add constraint fk_Scrap_receiver_id_User_id 
        foreign key (receiver_id) 
        references User (id);

    alter table Scrap 
        add index fk_Scrap_author_id_User_id (author_id), 
        add constraint fk_Scrap_author_id_User_id 
        foreign key (author_id) 
        references User (id);

    alter table friend_reference 
        add index fk_Friend_Reference_user_id_User_id (user_id), 
        add constraint fk_Friend_Reference_user_id_User_id 
        foreign key (user_id) 
        references User (id);

    alter table friend_reference 
        add index fk_Friend_Reference_friend_id_User_id (friend_id), 
        add constraint fk_Friend_Reference_friend_id_User_id 
        foreign key (friend_id) 
        references User (id);
