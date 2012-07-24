package com.buddycloud.mediaserver.web;

import java.util.ArrayList;
import java.util.List;

import org.restlet.Response;
import org.restlet.data.ChallengeRequest;
import org.restlet.data.ChallengeScheme;
import org.restlet.representation.Representation;
import org.restlet.resource.ServerResource;

import com.buddycloud.mediaserver.xmpp.AuthVerifier;
import com.buddycloud.mediaserver.xmpp.XMPPToolBox;

public abstract class MediaServerResource extends ServerResource {
	
	protected boolean verifyRequest(String userId, String token, String url) {
		AuthVerifier authClient = XMPPToolBox.getInstance().getAuthClient();
			
		return authClient.verifyUser(userId, token, url);
	}
	
	protected Representation authenticationResponse() {
		List<ChallengeRequest> challengeRequests = new ArrayList<ChallengeRequest>();
		challengeRequests.add(new ChallengeRequest(ChallengeScheme.HTTP_BASIC, MediaServerApplication.REALM));

		Response response = getResponse();
		response.setChallengeRequests(challengeRequests);
		
		return response.getEntity();
	}
}