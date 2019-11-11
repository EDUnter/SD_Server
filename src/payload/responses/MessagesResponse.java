package payload.responses;

import java.util.Map;

public class MessagesResponse {
	private Map<Long, String> mapUsers;
	private Map<Long, String> mapMessages;

	public MessagesResponse(Map<Long, String> mapUsers, Map<Long, String> mapMessages) {
		this.mapUsers = mapUsers;
		this.mapMessages = mapMessages;
	}

	public Map<Long, String> getMapUsers() {
		return mapUsers;
	}

	public void setMapUsers(Map<Long, String> mapUsers) {
		this.mapUsers = mapUsers;
	}

	public Map<Long, String> getMapMessages() {
		return mapMessages;
	}

	public void setMapMessages(Map<Long, String> mapMessages) {
		this.mapMessages = mapMessages;
	}
}
