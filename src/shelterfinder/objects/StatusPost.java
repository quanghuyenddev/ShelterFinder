package shelterfinder.objects;

public class StatusPost {
	private int imagesAvatar;
	private String userName;
	private String dateUp;
	private String address;
	private String area;
	private String price;
	private int imagesDescription;
	public int getImagesAvatar() {
		return imagesAvatar;
	}
	
	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getPrice() {
		return price;
	}

	public void setPrice(String price) {
		this.price = price;
	}

	public void setImagesAvatar(int imagesAvatar) {
		this.imagesAvatar = imagesAvatar;
	}
	public String getDateUp() {
		return dateUp;
	}
	public void setDateUp(String dateUp) {
		this.dateUp = dateUp;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public String getArea() {
		return area;
	}
	public void setArea(String area) {
		this.area = area;
	}
	public int getImagesDescription() {
		return imagesDescription;
	}
	public void setImagesDescription(int imagesDescription) {
		this.imagesDescription = imagesDescription;
	}
	public StatusPost(){
		super();
	}
	public StatusPost(int imagesAvatar,String userName, String dateUp, String address,
			String area, String price, int imagesDescription) {
		super();
		this.imagesAvatar = imagesAvatar;
		this.userName=userName;
		this.dateUp = dateUp;
		this.address = address;
		this.area = area;
		this.price = price;
		this.imagesDescription = imagesDescription;
	}
	
}
