package org.service;

import org.model.SubjectModel;
import org.repository.SubjectRepository;

public class SubjectService {
	SubjectRepository repo = new SubjectRepository();

	public boolean addSubject(SubjectModel model) {
	return repo.addSubject(model);
	}
	}