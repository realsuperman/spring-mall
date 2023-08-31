package com.example.shopping.validation;


import  com.example.shopping.exception.MessageException;

public class Validation {
    public static Validation validation;

    static {
        validation = new Validation();
    }

    public Validation getValidation() {
        return validation;
    }

    public boolean validateInt(String fieldName, Object value, boolean isUse, int min, int max) {
        try {
            int intValue = Integer.parseInt((String) value);

            if (isUse && (intValue < min || intValue > max)) {
                throw new MessageException(fieldName + "값은 " + min + " ~ " + max + " 사이의 값을 가져야 합니다.");
            }
        } catch (NumberFormatException e) {
            throw new MessageException(fieldName + " 해당 값은 int여야 합니다.");
        }
        return true;
    }

    public boolean validateLong(String fieldName, Object value, boolean isUse, int min, int max) {
        try {
            long longValue = Long.parseLong((String) value);

            if (isUse && (longValue < min || longValue > max)) {
                throw new MessageException(fieldName + "값은 " + min + " ~ " + max + " 사이의 값을 가져야 합니다.");
            }
        } catch (NumberFormatException e) {
            throw new MessageException(fieldName + " 해당 값은 long이여야 합니다.");
        }
        return true;
    }

    /*
        size는 db 컬럼의 사이즈
     */
    public boolean validateString(String fieldName, Object value, int size) {
        validateNull(fieldName, value);
        String strValue = value.toString();

        int charSize = 0;

        for (int i = 0; i < strValue.length(); i++) {
            charSize += (strValue.charAt(i) < 128) ? 1 : 2; // ASCII는 1바이트, 나머지는 2바이트
        }

        if (charSize > size) {
            throw new MessageException(fieldName + "값의 최대 사이즈를 초과 하였습니다.");
        }

        return true;
    }

    public boolean validateNull(String fieldName, Object value) {
        if (value == null || value.toString().isEmpty()) {
            throw new MessageException(fieldName + "(이)가 비어 있을 수 없습니다.");
        }
        return true;
    }

    public void validateEmail(String userEmail) {

        int at = userEmail.indexOf("@");
        int dot = userEmail.indexOf(".");
        if (at == -1 || dot == -1 || at > dot) {
            throw new MessageException("이메일 양식에 맞지 않습니다.");
        }
    }

    public void validatePassword(String password) {

        if (password.length() < 5) {
            throw new MessageException("비밀번호는 5글자 이상 가능합니다.");
        }
    }


}
