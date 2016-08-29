package com.galaxyinternet.model;

import java.util.List;

public class Resource extends BaseModel
{
	private static final long serialVersionUID = 1L;

    private Long parentId;

    private String resourceCode;

    private String resourceName;

    private String resourceType;

    private String resourceDesc;

    private String resourceUrl;
    
    private String resourceIcon;

    private Integer resourceOrder;

    private String systemCode;
    
    private String resourceStatus;
    
    private List<Resource> children;

    public String getResourceStatus()
	{
		return resourceStatus;
	}

	public void setResourceStatus(String resourceStatus)
	{
		this.resourceStatus = resourceStatus;
	}

	public Long getParentId() {
        return parentId;
    }

    public void setParentId(Long parentId) {
        this.parentId = parentId;
    }

    public String getResourceCode() {
        return resourceCode;
    }

    public void setResourceCode(String resourceCode) {
        this.resourceCode = resourceCode == null ? null : resourceCode.trim();
    }

    public String getResourceName() {
        return resourceName;
    }

    public void setResourceName(String resourceName) {
        this.resourceName = resourceName == null ? null : resourceName.trim();
    }

    public String getResourceType() {
        return resourceType;
    }

    public void setResourceType(String resourceType) {
        this.resourceType = resourceType == null ? null : resourceType.trim();
    }

    public String getResourceDesc() {
        return resourceDesc;
    }

    public void setResourceDesc(String resourceDesc) {
        this.resourceDesc = resourceDesc == null ? null : resourceDesc.trim();
    }

    public String getResourceUrl() {
        return resourceUrl;
    }

    public void setResourceUrl(String resourceUrl) {
        this.resourceUrl = resourceUrl == null ? null : resourceUrl.trim();
    }

    public Integer getResourceOrder() {
        return resourceOrder;
    }

    public void setResourceOrder(Integer resourceOrder) {
        this.resourceOrder = resourceOrder;
    }

    public String getSystemCode() {
        return systemCode;
    }

    public void setSystemCode(String systemCode) {
        this.systemCode = systemCode == null ? null : systemCode.trim();
    }

	public List<Resource> getChildren()
	{
		return children;
	}

	public void setChildren(List<Resource> children)
	{
		this.children = children;
	}
	
	public String getResourceIcon()
	{
		return resourceIcon;
	}

	public void setResourceIcon(String resourceIcon)
	{
		this.resourceIcon = resourceIcon;
	}

	@Override
	public String toString()
	{
		return "Resource[id="+id+";parentId="+parentId+";resourceCode="+resourceCode+";resourceName="+resourceName+"]"+super.toString();
	}
    
}