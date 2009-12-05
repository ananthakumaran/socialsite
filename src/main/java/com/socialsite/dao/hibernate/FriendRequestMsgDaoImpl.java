package com.socialsite.dao.hibernate;

import com.socialsite.dao.FriendRequestMsgDao;
import com.socialsite.persistence.FriendRequestMsg;

public class FriendRequestMsgDaoImpl extends MessageDaoImpl<FriendRequestMsg>
		implements FriendRequestMsgDao
{

	public FriendRequestMsgDaoImpl()
	{
		super(FriendRequestMsg.class);
	}
}
