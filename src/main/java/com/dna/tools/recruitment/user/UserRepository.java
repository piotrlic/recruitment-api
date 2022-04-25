package com.dna.tools.recruitment.user;

import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
interface UserRepository {
    @Select("SELECT id from USERS where name = #{name}")
    Long getUserIdByName(final String name);

    @Select("SELECT id, login, name, creation_date as creationDate from USERS")
    List<ReadUserDTO> getAllUsers();

    @Insert("INSERT INTO USERS (login, password_hash, name, creation_date) VALUES (#{login}, #{password}, #{name}, #{creationDate}) ")
    void create(User user);

    @Update("UPDATE USERS SET name = #{name} WHERE login = #{login} ")
    void update(UpdateUserDTO user);

    @Delete("DELETE FROM USERS WHERE id = #{id} ")
    void delete(Long id);

    @Select("SELECT id, login, name, creation_date as creationDate from USERS where login = #{login}")
    ReadUserDTO getUserByLogin(String login);
}
