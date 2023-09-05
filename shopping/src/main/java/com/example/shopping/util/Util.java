package com.example.shopping.util;

import com.example.shopping.domain.user.Consumer;

import javax.servlet.http.HttpSession;

public class Util {
    public static class Session {
        public static Consumer getUser(HttpSession session) {
            return (Consumer)session.getAttribute("login_user");
        }
    }
    public static class Img {
        public static String getThumbnail(String itemImagePath) {
            return "https://firebasestorage.googleapis.com/v0/b/shoppingmall-c6950.appspot.com/o/" + itemImagePath.split(";")[0] + "?alt=media";
        }
    }
}
