package org.tinygroup.bizframedslenterprise.impl.security;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

import org.tinygroup.bizframedslenterprise.inter.security.Encryption;

public class EncryptionMD5 implements Encryption{

	public String execute(String inputStr) {
		return MD5Util.GetMD5Code(inputStr);     
	}

}
