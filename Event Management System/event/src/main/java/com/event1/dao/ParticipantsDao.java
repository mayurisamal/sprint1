package com.event1.dao;

import com.event1.entity.Participants;
import java.util.List;

public interface ParticipantsDao {

	// Method to save a new participant to the database
	void createParticipant(Participants participant);

	// Method to get one participant by their ID
	Participants getParticipant(String participantId);

	// Method to get all participants from the database
	List<Participants> getAllParticipants();

	// Method to update an existing participant's details
	void updateParticipant(Participants participant);

	// Method to delete a participant using their ID
	void deleteParticipant(String participantId);

	// Get list of participants based on their status
	List<Participants> getParticipantsByStatus(String status);
}
