/*************************************************************************
 * Copyright 2009-2013 Eucalyptus Systems, Inc.
 *
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation; version 3 of the License.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program.  If not, see http://www.gnu.org/licenses/.
 *
 * Please contact Eucalyptus Systems, Inc., 6755 Hollister Ave., Goleta
 * CA 93117, USA or visit http://www.eucalyptus.com/licenses/ if you need
 * additional information or have any questions.
 *
 * This file may incorporate work covered under the following copyright
 * and permission notice:
 *
 *   Software License Agreement (BSD License)
 *
 *   Copyright (c) 2008, Regents of the University of California
 *   All rights reserved.
 *
 *   Redistribution and use of this software in source and binary forms,
 *   with or without modification, are permitted provided that the
 *   following conditions are met:
 *
 *     Redistributions of source code must retain the above copyright
 *     notice, this list of conditions and the following disclaimer.
 *
 *     Redistributions in binary form must reproduce the above copyright
 *     notice, this list of conditions and the following disclaimer
 *     in the documentation and/or other materials provided with the
 *     distribution.
 *
 *   THIS SOFTWARE IS PROVIDED BY THE COPYRIGHT HOLDERS AND CONTRIBUTORS
 *   "AS IS" AND ANY EXPRESS OR IMPLIED WARRANTIES, INCLUDING, BUT NOT
 *   LIMITED TO, THE IMPLIED WARRANTIES OF MERCHANTABILITY AND FITNESS
 *   FOR A PARTICULAR PURPOSE ARE DISCLAIMED. IN NO EVENT SHALL THE
 *   COPYRIGHT OWNER OR CONTRIBUTORS BE LIABLE FOR ANY DIRECT, INDIRECT,
 *   INCIDENTAL, SPECIAL, EXEMPLARY, OR CONSEQUENTIAL DAMAGES (INCLUDING,
 *   BUT NOT LIMITED TO, PROCUREMENT OF SUBSTITUTE GOODS OR SERVICES;
 *   LOSS OF USE, DATA, OR PROFITS; OR BUSINESS INTERRUPTION) HOWEVER
 *   CAUSED AND ON ANY THEORY OF LIABILITY, WHETHER IN CONTRACT, STRICT
 *   LIABILITY, OR TORT (INCLUDING NEGLIGENCE OR OTHERWISE) ARISING IN
 *   ANY WAY OUT OF THE USE OF THIS SOFTWARE, EVEN IF ADVISED OF THE
 *   POSSIBILITY OF SUCH DAMAGE. USERS OF THIS SOFTWARE ACKNOWLEDGE
 *   THE POSSIBLE PRESENCE OF OTHER OPEN SOURCE LICENSED MATERIAL,
 *   COPYRIGHTED MATERIAL OR PATENTED MATERIAL IN THIS SOFTWARE,
 *   AND IF ANY SUCH MATERIAL IS DISCOVERED THE PARTY DISCOVERING
 *   IT MAY INFORM DR. RICH WOLSKI AT THE UNIVERSITY OF CALIFORNIA,
 *   SANTA BARBARA WHO WILL THEN ASCERTAIN THE MOST APPROPRIATE REMEDY,
 *   WHICH IN THE REGENTS' DISCRETION MAY INCLUDE, WITHOUT LIMITATION,
 *   REPLACEMENT OF THE CODE SO IDENTIFIED, LICENSING OF THE CODE SO
 *   IDENTIFIED, OR WITHDRAWAL OF THE CODE CAPABILITY TO THE EXTENT
 *   NEEDED TO COMPLY WITH ANY SUCH LICENSES OR RIGHTS.
 ************************************************************************/

package com.eucalyptus.ws.util;

import org.apache.ws.security.WSSecurityException;
import org.apache.ws.security.components.crypto.Crypto;
import org.apache.ws.security.components.crypto.CryptoType;
import com.eucalyptus.component.ComponentId;
import com.eucalyptus.component.auth.SystemCredentials;

import java.io.InputStream;
import java.security.PrivateKey;
import java.security.PublicKey;
import javax.security.auth.callback.CallbackHandler;
import java.security.cert.CertificateFactory;
import java.security.cert.X509Certificate;

public class CredentialProxy implements Crypto {

  private final Class<? extends ComponentId> componentId;
  public CredentialProxy( Class<? extends ComponentId> componentId ) {
    this.componentId = componentId;
  }

  @Override
  public X509Certificate[] getCertificatesFromBytes( final byte[] data ) throws WSSecurityException {
    return new X509Certificate[] { SystemCredentials.lookup( this.componentId ).getCertificate( ) };
  }

  @Override
  public X509Certificate[] getX509Certificates( final CryptoType cryptoType ) throws WSSecurityException {
    return new X509Certificate[] { SystemCredentials.lookup( this.componentId ).getCertificate( ) };
  }

  @Override
  public PrivateKey getPrivateKey( final String alias, final String password ) throws WSSecurityException {
    return SystemCredentials.lookup( this.componentId ).getPrivateKey( );
  }

  @Override
  public PrivateKey getPrivateKey( final X509Certificate certificate, final CallbackHandler callbackHandler ) throws WSSecurityException {
    return SystemCredentials.lookup( this.componentId ).getPrivateKey( );
  }

  @Override public void setCryptoProvider( final String provider ) { }
  @Override public String getCryptoProvider() { return null; }
  @Override public void setCertificateFactory( final String provider, final CertificateFactory certFactory) { }
  @Override public void setDefaultX509Identifier( final String identifier) { }
  @Override public X509Certificate loadCertificate( final InputStream inputStream ) throws WSSecurityException { return null; }
  @Override public String getX509Identifier( final X509Certificate cert ) throws WSSecurityException { return null; }
  @Override public byte[] getBytesFromCertificates( final X509Certificate[] certs ) throws WSSecurityException { return new byte[0]; }
  @Override public byte[] getSKIBytesFromCert( final X509Certificate cert ) throws WSSecurityException { return new byte[0]; }
  @Override public String getDefaultX509Identifier() throws WSSecurityException { return null; }
  @Override public CertificateFactory getCertificateFactory() throws WSSecurityException { return null; }
  @Override public boolean verifyTrust( final X509Certificate[] certs ) throws WSSecurityException { return false; }
  @Override public boolean verifyTrust( final X509Certificate[] certs, boolean enableRevocation ) throws WSSecurityException { return false; }
  @Override public boolean verifyTrust( final PublicKey publicKey ) throws WSSecurityException { return false; }
}
