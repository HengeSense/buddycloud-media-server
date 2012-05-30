package com.buddycloud.mediaserver.commons;

public class Constants {
	
	private Constants() {}

	//URL arguments
	public static final String ENTITY_ARG = "entityId";
	public static final String MEDIA_ARG = "mediaId";

	//URLs
	public static final String POST_MEDIA_URL = "/media/{" + ENTITY_ARG + "}";
	public static final String GET_MEDIA_URL = "/media/{" + ENTITY_ARG + "}/{" + MEDIA_ARG + "}";
	
	public static final String POST_AVATAR_URL = "/media/avatar/{" + ENTITY_ARG + "}";
	public static final String GET_AVATAR_URL = "/media/avatar/{" + ENTITY_ARG + "}/{" + MEDIA_ARG + "}";
	public static final String PUT_AVATAR_URL = "/media/avatar/{" + ENTITY_ARG + "}/{" + MEDIA_ARG + "}";
	
	//Storage constants
	public static final String FILE_FIELD = "binaryFile";
	public static final String BODY_FIELD = "body";
	
	//Properties file constants
	public static final String MEDIA_SIZE_LIMIT_PROPERTY = "media.sizelimit";
	public static final String MEDIA_STORAGE_ROOT_PROPERTY = "media.channel.root";
	
	public static final String JDBC_DRIVER_CLASS_PROPERTY = "jdbc.driver.class";
	public static final String JDBC_DB_URL_PROPERTY = "jdbc.db.url";
}
