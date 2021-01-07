package com.example.ysh.recyclerview;

public class Dictionary {
    private String nickname;
    private String city;
    private String contents;
    private String body;

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getContents() {
        return contents;
    }

    public void setContents(String contents) {
        this.contents = contents;
    }

    public String getBody() {
        return body;
    }

    public void setBody(String body) {
        this.body = body;
    }

    public Dictionary(String nickname, String city, String contents, String body) {
        this.nickname = nickname;
        this.city = city;
        this.contents = contents;
        this.body = body;
    }

}
