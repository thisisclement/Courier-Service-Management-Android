package com.example.jobs;


public class jobObject {
	private int jobsId;
    private String name;
    private String address;
    private String unitNo;
    private String mobile; 
    private String type;
    private String postal;
    

    public jobObject(String name, String address, String mobile, int jobsId, String unitNo, 
    		String type, String postal){
        
        this.name = name;
        this.address = address;
        this.mobile = mobile;
        this.jobsId = jobsId;
        this.unitNo = unitNo;
        this.type = type;
        this.postal = postal;
    }

    public String getName(){
        return this.name;
    }

    public String getAddress(){
        return this.address;
    }

    public String getMobile(){
        return this.mobile;
    }
    
    public int getJobId(){
        return this.jobsId;
    }
    
    public String getUnitNo(){
        return this.unitNo;
    }
    
    public String getType(){
        return this.type;
    }
    
    public String getPostal(){
    	return this.postal;
    }

}
