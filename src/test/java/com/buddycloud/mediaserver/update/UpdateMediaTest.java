package com.buddycloud.mediaserver.update;

import static junit.framework.Assert.assertEquals;

import java.io.File;

import org.apache.commons.io.FileUtils;
import org.junit.Test;
import org.restlet.data.ChallengeScheme;
import org.restlet.ext.html.FormData;
import org.restlet.ext.html.FormDataSet;
import org.restlet.representation.Representation;
import org.restlet.representation.StringRepresentation;
import org.restlet.resource.ClientResource;

import com.buddycloud.mediaserver.business.model.Media;
import com.buddycloud.mediaserver.commons.Constants;
import com.buddycloud.mediaserver.MediaServerTest;

public class UpdateMediaTest extends MediaServerTest {
	
	public void testTearDown() throws Exception {
		FileUtils.cleanDirectory(new File(configuration.getProperty(Constants.MEDIA_STORAGE_ROOT_PROPERTY) + 
				File.separator + BASE_CHANNEL));
		
		dataSource.deleteMedia(MEDIA_ID);
	}
	
	@Override
	protected void testSetUp() throws Exception {
		File destDir = new File(configuration.getProperty(Constants.MEDIA_STORAGE_ROOT_PROPERTY) + File.separator + BASE_CHANNEL);
		if (!destDir.mkdir()) {
			FileUtils.cleanDirectory(destDir);
		}
		
		FileUtils.copyFile(new File(TESTFILE_PATH + TESTMEDIA_NAME), new File(destDir + File.separator + MEDIA_ID));
		
		Media media = buildMedia(MEDIA_ID, TESTFILE_PATH + TESTMEDIA_NAME);
		dataSource.storeMedia(media);
	}
	
	@Test
	public void anonymousSuccessfulUpload() throws Exception {
		// file fields
		String title = "New Image";
		String description = "New Description";

		ClientResource client = new ClientResource(BASE_URL + "/media/" + BASE_CHANNEL + "/" + MEDIA_ID);
		client.setChallengeResponse(ChallengeScheme.HTTP_BASIC, BASE_USER, BASE_TOKEN);

		FormDataSet form = new FormDataSet();
		form.setMultipart(true);
		form.getEntries().add(new FormData(Constants.TITLE_FIELD,
		new StringRepresentation(title)));	
		form.getEntries().add(new FormData(Constants.DESC_FIELD,
		new StringRepresentation(description)));
		
		Representation result = client.put(form);
		Media media = gson.fromJson(result.getText(), Media.class);

		// verify if resultant media has the passed attributes
		assertEquals(title, media.getTitle());
		assertEquals(description, media.getDescription());
	}

}