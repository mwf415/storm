package com.youyicn.entity.cycle;

import java.sql.Timestamp;
/**
 * 公告通知类
 * @author Administrator
 *
 */
public class Message {
	private int messageId;
	private String messageTitle;
	private String messageTxt;
	private String receiver;
	private Timestamp createTime;
	private String sender;
	private String senderName;
	public int getMessageId() {
		return messageId;
	}
	public void setMessageId(int messageId) {
		this.messageId = messageId;
	}
	public String getMessageTitle() {
		return messageTitle;
	}
	public void setMessageTitle(String messageTitle) {
		this.messageTitle = messageTitle;
	}
	public String getMessageTxt() {
		return messageTxt;
	}
	public void setMessageTxt(String messageTxt) {
		this.messageTxt = messageTxt;
	}
	public String getReceiver() {
		return receiver;
	}
	public void setReceiver(String receiver) {
		this.receiver = receiver;
	}
	public Timestamp getCreateTime() {
		return createTime;
	}
	public void setCreateTime(Timestamp createTime) {
		this.createTime = createTime;
	}
	public String getSender() {
		return sender;
	}
	public void setSender(String sender) {
		this.sender = sender;
	}
	public String getSenderName() {
		return senderName;
	}
	public void setSenderName(String senderName) {
		this.senderName = senderName;
	}
	@Override
	public String toString() {
		return "Message [messageId=" + messageId + ", messageTitle="
				+ messageTitle + ", messageTxt=" + messageTxt + ", receiver="
				+ receiver + ", createTime=" + createTime + ", sender="
				+ sender + ", senderName=" + senderName + "]";
	}
	
}
