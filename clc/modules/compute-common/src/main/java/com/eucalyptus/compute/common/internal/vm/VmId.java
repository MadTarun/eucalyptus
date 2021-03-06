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

package com.eucalyptus.compute.common.internal.vm;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import org.hibernate.annotations.Parent;

@Embeddable
public class VmId {
  @Parent
  private VmInstance vmInstance;
  @Column( name = "metadata_vm_reservation_id" )
  private String     reservationId;
  @Column( name = "metadata_vm_client_token", updatable = false )
  private String     clientToken;
  @Column( name = "metadata_vm_client_token_unique", unique = true, updatable = false )
  private String     uniqueClientToken;
  @Column( name = "metadata_vm_instance_id" )
  private String     instanceId;
  
  VmId( ) {
  }
  
  public VmId( String reservationId, String instanceId, String clientToken, String uniqueClientToken) {
    this.reservationId = reservationId;
    this.clientToken = clientToken;
    this.uniqueClientToken = uniqueClientToken;
    this.instanceId = instanceId;
  }
  
  private VmInstance getVmInstance( ) {
    return this.vmInstance;
  }
  
  public String getReservationId( ) {
    return this.reservationId;
  }

  public String getInstanceId( ) {
    return this.instanceId;
  }

  public String getClientToken( ) {
    return this.clientToken;
  }

  public String getUniqueClientToken( ) {
    return this.uniqueClientToken;
  }

  private void setVmInstance( VmInstance vmInstance ) {
    this.vmInstance = vmInstance;
  }
  
  private void setReservationId( String reservationId ) {
    this.reservationId = reservationId;
  }

  private void setInstanceId( String instanceId ) {
    this.instanceId = instanceId;
  }

  private void setClientToken( String clientToken ) {
    this.clientToken = clientToken;
  }

  private void setUniqueClientToken( String uniqueClientToken ) {
    this.uniqueClientToken = uniqueClientToken;
  }

  @Override
  public String toString( ) {
    StringBuilder builder = new StringBuilder( );
    builder.append( "VmId:" );
    if ( this.reservationId != null ) builder.append( "reservationId=" ).append( this.reservationId ).append( ":" );
    if ( this.clientToken != null ) builder.append( "clientToken=" ).append( this.clientToken ).append( ":" );
    if ( this.instanceId != null ) builder.append( "instanceId=" ).append( this.instanceId );
    return builder.toString( );
  }

  @Override
  public int hashCode( ) {
    final int prime = 31;
    int result = 1;
    result = prime * result + ( ( this.instanceId == null )
      ? 0
      : this.instanceId.hashCode( ) );
    return result;
  }

  @Override
  public boolean equals( Object obj ) {
    if ( this == obj ) {
      return true;
    }
    if ( obj == null ) {
      return false;
    }
    if ( getClass( ) != obj.getClass( ) ) {
      return false;
    }
    VmId other = ( VmId ) obj;
    if ( this.instanceId == null ) {
      if ( other.instanceId != null ) {
        return false;
      }
    } else if ( !this.instanceId.equals( other.instanceId ) ) {
      return false;
    }
    return true;
  }
}
