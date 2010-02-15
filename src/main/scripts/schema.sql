--
--     Copyright SocialSite (C) 2009
--
--     This program is free software: you can redistribute it and/or modify
--     it under the terms of the GNU General Public License as published by
--     the Free Software Foundation, either version 3 of the License, or
--     (at your option) any later version.
--
--     This program is distributed in the hope that it will be useful,
--     but WITHOUT ANY WARRANTY; without even the implied warranty of
--     MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
--     GNU General Public License for more details.
--
--     You should have received a copy of the GNU General Public License
--     along with this program.  If not, see <http://www.gnu.org/licenses/>.
--


    alter table ADMIN 
        drop 
        foreign key FK3B40B2FE8BA0282;

    alter table ANSWER 
        drop 
        foreign key fk_Answer_user_id_User_id;

    alter table ANSWER 
        drop 
        foreign key fk_Answer_question_id_Question_id;

    alter table COMMENT 
        drop 
        foreign key fk_Comment_answer_id_Answer_id;

    alter table COMMENT 
        drop 
        foreign key fk_Comment_user_id_User_id;

    alter table COURSE 
        drop 
        foreign key fk_Course_staff_id_Staff_id;

    alter table COURSE 
        drop 
        foreign key fk_Course_university_id_University_id;

    alter table FRIEND_REQUEST_MSG 
        drop 
        foreign key FKED4A6970E1E64E06;

    alter table FRIEND_REQUEST_MSG 
        drop 
        foreign key fk_FRIEND_REQUEST_MSG_sender_id_User_id;

    alter table INFO_MSG 
        drop 
        foreign key FK3954B050E1E64E06;

    alter table INFO_MSG 
        drop 
        foreign key fk_INFO_MSG_sender_id_User_id;

    alter table Profile 
        drop 
        foreign key fk_Profile_user_id_User_id;

    alter table QUESTION 
        drop 
        foreign key fk_Question_user_id_User_id;

    alter table QUESTION 
        drop 
        foreign key fk_Question_course_id_Course_id;

    alter table STAFF 
        drop 
        foreign key FK4B8CAC0E8BA0282;

    alter table STAFF 
        drop 
        foreign key fk_Staff_university_id_University_id;

    alter table STUDENT 
        drop 
        foreign key FKBACA0E1BE8BA0282;

    alter table STUDENT_COURSE 
        drop 
        foreign key fk_STUDENT_COURSE_student_id_Student_id;

    alter table STUDENT_COURSE 
        drop 
        foreign key fk_STUDENT_COURSE_course_id_Course_id;

    alter table Scrap 
        drop 
        foreign key fk_Scrap_receiver_id_User_id;

    alter table Scrap 
        drop 
        foreign key fk_Scrap_author_id_User_id;

    alter table UNIVERSITY 
        drop 
        foreign key fk_University_admin_id_Admin_id;

    alter table friend_reference 
        drop 
        foreign key fk_Friend_Reference_user_id_User_id;

    alter table friend_reference 
        drop 
        foreign key fk_Friend_Reference_friend_id_User_id;

    alter table message_user 
        drop 
        foreign key message_user_user_id_User_id;

    alter table message_user 
        drop 
        foreign key message_user_message_id_Message_id;

    drop table if exists ADMIN;

    drop table if exists ANSWER;

    drop table if exists COMMENT;

    drop table if exists COURSE;

    drop table if exists FRIEND_REQUEST_MSG;

    drop table if exists INFO_MSG;

    drop table if exists MESSAGE;

    drop table if exists Profile;

    drop table if exists QUESTION;

    drop table if exists STAFF;

    drop table if exists STUDENT;

    drop table if exists STUDENT_COURSE;

    drop table if exists Scrap;

    drop table if exists UNIVERSITY;

    drop table if exists User;

    drop table if exists friend_reference;

    drop table if exists message_user;

    create table ADMIN (
        id bigint not null,
        primary key (id)
    );

    create table ANSWER (
        id bigint not null auto_increment,
        time datetime,
        text text,
        user_id bigint,
        question_id bigint,
        primary key (id)
    );

    create table COMMENT (
        id bigint not null auto_increment,
        time datetime,
        text text,
        answer_id bigint,
        user_id bigint,
        primary key (id)
    );

    create table COURSE (
        id bigint not null auto_increment,
        name varchar(255),
        image mediumblob,
        thumb mediumblob,
        lastModified datetime,
        university_id bigint,
        staff_id bigint,
        primary key (id)
    );

    create table FRIEND_REQUEST_MSG (
        id bigint not null,
        message varchar(255),
        sender_id bigint,
        primary key (id)
    );

    create table INFO_MSG (
        id bigint not null,
        message text,
        sender_id bigint,
        primary key (id)
    );

    create table MESSAGE (
        id bigint not null auto_increment,
        time datetime,
        primary key (id)
    );

    create table Profile (
        user_id bigint not null,
        firstName varchar(255),
        lastName varchar(255),
        email varchar(255),
        sex varchar(255),
        currentCity varchar(255),
        homeTown varchar(255),
        relationshipStatus varchar(255),
        politicalView text,
        religiousView text,
        activities text,
        interests text,
        favouriteMusic text,
        favouriteMovies text,
        favouriteTvShows text,
        favouriteQuotations text,
        favouriteBooks text,
        aboutMe text,
        mobilePhone varchar(255),
        landPhone varchar(255),
        address varchar(255),
        city varchar(255),
        neighborhood varchar(255),
        zip integer,
        website varchar(255),
        college varchar(255),
        image mediumblob,
        thumb mediumblob,
        value varchar(255),
        privacy varchar(255),
        primary key (user_id)
    );

    create table QUESTION (
        id bigint not null auto_increment,
        heading varchar(255),
        time datetime,
        text text,
        course_id bigint,
        user_id bigint,
        primary key (id)
    );

    create table STAFF (
        id bigint not null,
        university_id bigint,
        primary key (id)
    );

    create table STUDENT (
        id bigint not null,
        primary key (id)
    );

    create table STUDENT_COURSE (
        student_id bigint not null,
        course_id bigint not null,
        primary key (course_id, student_id)
    );

    create table Scrap (
        id bigint not null auto_increment,
        message text not null,
        time datetime not null,
        author_id bigint not null,
        receiver_id bigint not null,
        primary key (id)
    );

    create table UNIVERSITY (
        id bigint not null auto_increment,
        name varchar(255),
        image mediumblob,
        thumb mediumblob,
        lastModified datetime,
        admin_id bigint,
        primary key (id)
    );

    create table User (
        id bigint not null auto_increment,
        userName varchar(255) not null unique,
        password varchar(255) not null,
        lastModified datetime,
        primary key (id)
    );

    create table friend_reference (
        friend_id bigint not null,
        user_id bigint not null,
        primary key (friend_id, user_id)
    );

    create table message_user (
        user_id bigint not null,
        message_id bigint not null,
        primary key (message_id, user_id)
    );

    alter table ADMIN 
        add index FK3B40B2FE8BA0282 (id), 
        add constraint FK3B40B2FE8BA0282 
        foreign key (id) 
        references User (id);

    alter table ANSWER 
        add index fk_Answer_user_id_User_id (user_id), 
        add constraint fk_Answer_user_id_User_id 
        foreign key (user_id) 
        references User (id);

    alter table ANSWER 
        add index fk_Answer_question_id_Question_id (question_id), 
        add constraint fk_Answer_question_id_Question_id 
        foreign key (question_id) 
        references QUESTION (id);

    alter table COMMENT 
        add index fk_Comment_answer_id_Answer_id (answer_id), 
        add constraint fk_Comment_answer_id_Answer_id 
        foreign key (answer_id) 
        references ANSWER (id);

    alter table COMMENT 
        add index fk_Comment_user_id_User_id (user_id), 
        add constraint fk_Comment_user_id_User_id 
        foreign key (user_id) 
        references User (id);

    alter table COURSE 
        add index fk_Course_staff_id_Staff_id (staff_id), 
        add constraint fk_Course_staff_id_Staff_id 
        foreign key (staff_id) 
        references STAFF (id);

    alter table COURSE 
        add index fk_Course_university_id_University_id (university_id), 
        add constraint fk_Course_university_id_University_id 
        foreign key (university_id) 
        references UNIVERSITY (id);

    alter table FRIEND_REQUEST_MSG 
        add index FKED4A6970E1E64E06 (id), 
        add constraint FKED4A6970E1E64E06 
        foreign key (id) 
        references MESSAGE (id);

    alter table FRIEND_REQUEST_MSG 
        add index fk_FRIEND_REQUEST_MSG_sender_id_User_id (sender_id), 
        add constraint fk_FRIEND_REQUEST_MSG_sender_id_User_id 
        foreign key (sender_id) 
        references User (id);

    alter table INFO_MSG 
        add index FK3954B050E1E64E06 (id), 
        add constraint FK3954B050E1E64E06 
        foreign key (id) 
        references MESSAGE (id);

    alter table INFO_MSG 
        add index fk_INFO_MSG_sender_id_User_id (sender_id), 
        add constraint fk_INFO_MSG_sender_id_User_id 
        foreign key (sender_id) 
        references User (id);

    alter table Profile 
        add index fk_Profile_user_id_User_id (user_id), 
        add constraint fk_Profile_user_id_User_id 
        foreign key (user_id) 
        references User (id);

    alter table QUESTION 
        add index fk_Question_user_id_User_id (user_id), 
        add constraint fk_Question_user_id_User_id 
        foreign key (user_id) 
        references User (id);

    alter table QUESTION 
        add index fk_Question_course_id_Course_id (course_id), 
        add constraint fk_Question_course_id_Course_id 
        foreign key (course_id) 
        references COURSE (id);

    alter table STAFF 
        add index FK4B8CAC0E8BA0282 (id), 
        add constraint FK4B8CAC0E8BA0282 
        foreign key (id) 
        references User (id);

    alter table STAFF 
        add index fk_Staff_university_id_University_id (university_id), 
        add constraint fk_Staff_university_id_University_id 
        foreign key (university_id) 
        references UNIVERSITY (id);

    alter table STUDENT 
        add index FKBACA0E1BE8BA0282 (id), 
        add constraint FKBACA0E1BE8BA0282 
        foreign key (id) 
        references User (id);

    alter table STUDENT_COURSE 
        add index fk_STUDENT_COURSE_student_id_Student_id (student_id), 
        add constraint fk_STUDENT_COURSE_student_id_Student_id 
        foreign key (student_id) 
        references STUDENT (id);

    alter table STUDENT_COURSE 
        add index fk_STUDENT_COURSE_course_id_Course_id (course_id), 
        add constraint fk_STUDENT_COURSE_course_id_Course_id 
        foreign key (course_id) 
        references COURSE (id);

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

    alter table UNIVERSITY 
        add index fk_University_admin_id_Admin_id (admin_id), 
        add constraint fk_University_admin_id_Admin_id 
        foreign key (admin_id) 
        references ADMIN (id);

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

    alter table message_user 
        add index message_user_user_id_User_id (user_id), 
        add constraint message_user_user_id_User_id 
        foreign key (user_id) 
        references User (id);

    alter table message_user 
        add index message_user_message_id_Message_id (message_id), 
        add constraint message_user_message_id_Message_id 
        foreign key (message_id) 
        references MESSAGE (id);
