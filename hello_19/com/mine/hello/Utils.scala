package com.mine.hello

import com.macasaet.fernet._

object Utils {
    def encrypt(key_str: String, acc: String): String = {
        val key = new Key(key_str);
        val token = Token.generate(key, acc)

        return token.serialise()
    }

    def decrypt(key_str: String, enc_acc: String): String = {
        val key = new Key(key_str);
        val token = Token.fromString(enc_acc);

        val validator = new StringValidator() {}
        val payload = token.validateAndDecrypt(key, validator)

        return payload
    }
}