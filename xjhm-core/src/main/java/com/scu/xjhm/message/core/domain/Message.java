package com.scu.xjhm.message.core.domain;

import java.util.Date;
//import javax.persistence.Id;
//import javax.persistence.Lob;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
//import javax.persistence.FetchType;
//import javax.persistence.OneToMany;
//import org.openkoala.koala.commons.domain.KoalaIDEntity;
import java.io.Serializable;

import com.scu.xjhm.common.domain.BaseAbstractEntity;

/**
 * Auto Generated Entity
 * 
 * @author ywang 
 * 
 */
@Entity
@Table(name="message")
public class Message extends BaseAbstractEntity {

	private static final long serialVersionUID = -776947557381252468L;

  @Column(name="IS_AUDIO")
    private int isAudio;
  

  @Column(name="AUDIO_FILEPATH")
    private String audioFilepath;
  

  @Column(name="NAME")
    private String name;
  

  @Column(name="PHONE_NUM")
    private String phoneNum;
  

  @Column(name="ADDR")
    private String addr;
  

  @Column(name="UNREAD")
    private String unread;
  

  @Column(name="CATEGORY_ID")
    private Long categoryId;
  

  @Column(name="AUDIO_SIZE")
    private Long audioSize;
  

  @Column(name="IS_DEAL")
    private String isDeal;
  

  @Column(name="CONTENT")
    private String content;
  

  @Column(name="BUILDING_ID")
    private Long buildingId;
  

  
    
  public int getIsAudio() {
		return isAudio;
  }
  
  public void setIsAudio(int isAudio) {
		this.isAudio = isAudio;
  }
  
  
    
  public String getAudioFilepath() {
		return audioFilepath;
  }
  
  public void setAudioFilepath(String audioFilepath) {
		this.audioFilepath = audioFilepath;
  }
  
  
    
  public String getName() {
		return name;
  }
  
  public void setName(String name) {
		this.name = name;
  }
  
  
    
  public String getPhoneNum() {
		return phoneNum;
  }
  
  public void setPhoneNum(String phoneNum) {
		this.phoneNum = phoneNum;
  }
  
  
  public String getAddr() {
		return addr;
  }
  
  public void setAddr(String addr) {
		this.addr = addr;
  }
  
  public String getUnread() {
		return unread;
  }
  
  public void setUnread(String unread) {
		this.unread = unread;
  }
    
  public Long getCategoryId() {
		return categoryId;
  }
  
  public void setCategoryId(Long categoryId) {
		this.categoryId = categoryId;
  }
  
  
    
  public Long getAudioSize() {
		return audioSize;
  }
  
  public void setAudioSize(Long audioSize) {
		this.audioSize = audioSize;
  }
  
  
    
  public String getIsDeal() {
		return isDeal;
  }
  
  public void setIsDeal(String isDeal) {
		this.isDeal = isDeal;
  }
    
  public String getContent() {
		return content;
  }
  
  public void setContent(String content) {
		this.content = content;
  }
  
  
    
  public Long getBuildingId() {
		return buildingId;
  }
  
  public void setBuildingId(Long buildingId) {
		this.buildingId = buildingId;
  }
  

   @Override
   public String[] businessKeys() {
     // TODO Auto-generated method stub
    return null;
   }

}