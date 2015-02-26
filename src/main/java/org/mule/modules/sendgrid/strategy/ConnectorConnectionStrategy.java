/**
 * (c) 2003-2015 MuleSoft, Inc. The software in this package is published under the terms of the CPAL v1.0 license,
 * a copy of which has been included with this distribution in the LICENSE.md file.
 */

package org.mule.modules.sendgrid.strategy;

import org.mule.api.ConnectionException;
import org.mule.api.ConnectionExceptionCode;
import org.mule.api.annotations.Connect;
import org.mule.api.annotations.ConnectionIdentifier;
import org.mule.api.annotations.Disconnect;
import org.mule.api.annotations.TestConnectivity;
import org.mule.api.annotations.ValidateConnection;
import org.mule.api.annotations.components.ConnectionManagement;
import org.mule.api.annotations.display.Password;
import org.mule.api.annotations.param.ConnectionKey;

import com.sendgrid.SendGrid;

/**
 * OAuth2 Connection Strategy
 *
 * @author MuleSoft, Inc.
 */
@ConnectionManagement(configElementName="config",friendlyName="Configuration")
public class ConnectorConnectionStrategy
{

	private SendGrid client;
	
    @Connect
    @TestConnectivity
    public void connect(@ConnectionKey String username, @Password String password)
        throws ConnectionException {
        try{
        	setClient(new SendGrid(username, password));        	
        }catch(Exception e){
        	throw new ConnectionException(ConnectionExceptionCode.INCORRECT_CREDENTIALS,"", e.getMessage());
        }
      
    }


    @Disconnect
    public void disconnect() {
    	client = null;
    }


    @ValidateConnection
    public boolean isConnected() {
        return client != null;
    }


    @ConnectionIdentifier
    public String connectionId() {
        return client.toString();
    }


	public SendGrid getClient() {
		return client;
	}


	public void setClient(SendGrid client) {
		this.client = client;
	}

}