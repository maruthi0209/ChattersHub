package com.niit.collaboration.dao;

import com.niit.collaboration.model.ProfilePicture;

public interface ProfilePictureDao
{
		void saveOrUpdateProfilePicture(ProfilePicture profilePicture);
		ProfilePicture getProfilePicture(String name);
		public void updateProfilePicture(ProfilePicture profilePicture);
}
