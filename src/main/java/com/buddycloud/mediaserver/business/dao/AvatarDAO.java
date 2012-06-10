package com.buddycloud.mediaserver.business.dao;

import java.io.File;
import java.io.IOException;
import java.util.Properties;

import org.apache.log4j.Logger;

import com.buddycloud.mediaserver.business.jdbc.MetadataSource;
import com.buddycloud.mediaserver.commons.exception.InvalidPreviewFormatException;
import com.buddycloud.mediaserver.commons.exception.MediaNotFoundException;
import com.buddycloud.mediaserver.commons.exception.MetadataSourceException;

public class AvatarDAO extends AbstractDAO {

	private static Logger LOGGER = Logger.getLogger(AvatarDAO.class);


	AvatarDAO(MetadataSource dataSource, Properties configuration) {
		super(dataSource, configuration);
	}


	public File getAvatar(String entityId) 
			throws MetadataSourceException, MediaNotFoundException, IOException, InvalidPreviewFormatException {
		String mediaId = dataSource.getEntityAvatarId(entityId);
		
		LOGGER.debug("Getting avatar. Entity ID: " + entityId);
		
		String fullDirectoryPath = getDirectory(entityId);

		File media = new File(fullDirectoryPath + File.separator + mediaId);

		if (!media.exists()) {
			throw new MediaNotFoundException(mediaId, entityId);
		}

		// Update last viewed date
		dataSource.updateMediaLastViewed(mediaId);

		return media;
	}

	public byte[] getAvatarPreview(String entityId, Integer maxHeight, Integer maxWidth) 
			throws MetadataSourceException, MediaNotFoundException, IOException, InvalidPreviewFormatException {
		String mediaId = dataSource.getEntityAvatarId(entityId);

		LOGGER.debug("Getting avatar preview. Avatar ID: " + entityId);
		
		return getPreview(entityId, mediaId, maxHeight, maxWidth, getDirectory(entityId));
	}
	
	public String getAvatarMediaType(String entityId) throws MetadataSourceException {
		String mediaId = dataSource.getEntityAvatarId(entityId);
		
		return dataSource.getMediaMimeType(mediaId);
	}
}