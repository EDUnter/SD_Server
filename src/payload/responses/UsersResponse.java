package payload.responses;

import java.util.Map;

public class UsersResponse {
	private Map<Long, String> users;

	public UsersResponse(Map<Long, String> users) {
		this.users = users;
	}

	public Map<Long, String> getUsers() {
		return users;
	}

	public void setUsers(Map<Long, String> users) {
		this.users = users;
	}
}
