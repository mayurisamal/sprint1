package com.event1.ServiceImpl;

import java.util.List;

import com.event1.Service.ParticipantsService;
import com.event1.dao.ParticipantsDao;
import com.event1.daoImpl.ParticipantsDaoImpl;
import com.event1.entity.Participants;

public class ParticipantsServiceImpl implements ParticipantsService {

	// Create an instance of ParticipantsDao to interact with the database
	ParticipantsDao participantsDao = new ParticipantsDaoImpl();

	// Method to create a new participant
	@Override
	public void createParticipant(Participants participant) {
		participantsDao.createParticipant(participant);  // Call the DAO to save the participant
	}

	// Method to retrieve a participant by their ID
	@Override
	public Participants getParticipant(String participantId) {
		return participantsDao.getParticipant(participantId);  // Call the DAO to get the participant
	}

	// Method to retrieve all participants
	@Override
	public List<Participants> getAllParticipants() {
		return participantsDao.getAllParticipants();  // Call the DAO to get all participants
	}

	// Method to update an existing participant's information
	@Override
	public void updateParticipant(Participants participant) {
		participantsDao.updateParticipant(participant);  // Call the DAO to update the participant
	}

	// Method to delete a participant by their ID
	@Override
	public void deleteParticipant(String participantId) {
		participantsDao.deleteParticipant(participantId);  // Call the DAO to delete the participant
	}

	// Method to retrieve participants based on their status (e.g., "confirmed", "pending")
	@Override
	public List<Participants> getParticipantsByStatus(String status) {
		return participantsDao.getParticipantsByStatus(status);  // Call the DAO to get participants by status
	}
}
