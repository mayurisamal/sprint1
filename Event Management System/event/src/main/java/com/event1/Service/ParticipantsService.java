package com.event1.Service;

import com.event1.entity.Participants;
import java.util.List;

public interface ParticipantsService {

	// Create a new participant
	void createParticipant(Participants participant);

	// Retrieve a participant by their ID
	Participants getParticipant(String participantId);

	// Retrieve a list of all participants
	List<Participants> getAllParticipants();

	// Update an existing participant's information
	void updateParticipant(Participants participant);

	// Delete a participant by their ID
	void deleteParticipant(String participantId);

	// Retrieve a list of participants by their status
	List<Participants> getParticipantsByStatus(String status);
}
