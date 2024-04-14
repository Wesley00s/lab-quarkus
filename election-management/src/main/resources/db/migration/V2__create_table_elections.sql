CREATE TABLE Election (
    id VARCHAR(40) NOT NULL,
    create_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    update_at DATETIME DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
    CONSTRAINT pk_elections PRIMARY KEY (id)
);

CREATE TABLE Election_candidate (
    election_id VARCHAR(40) NOT NULL,
    candidate_id VARCHAR(40) NOT NULL,
    votes INTEGER DEFAULT 0,
    CONSTRAINT pk_elections_candidate PRIMARY KEY (election_id, candidate_id)
);

INSERT INTO Candidate (id, photo, given_name, family_name, email, phone, job_title) VALUES
('mnopqrst-uvwx-1234-5678-90abcdef1234', 'https://robohash.org/mno.png', 'Mason', 'Jones', 'alexander.wang@example.com', '9876543-2109', 'Software Engineer'),
('34567890-abcd-ef12-3456-7890abcdef123', 'https://robohash.org/vwx.png', 'Liam', 'Miller', 'alexander.wang@example.com', '3456789-0123', 'Project Manager'),
('23456789-0abc-def1-2345-67890abcdef12', 'https://robohash.org/pqr.png', 'Liam', 'Davis', 'michael.nguyen@example.com', '8901234-5678', 'Data Analyst'),
('klmnopqrst-0abc-def1-2345-670abcdef12', 'https://robohash.org/def.png', 'Sophia', 'Johnson', 'david.brown@example.com', '5678901-2345', 'Project Manager'),
('56789012-cdef-1234-5678-90abcdef123456', 'https://robohash.org/yz.png', 'Noah', 'Smith', 'alexander.wang@example.com', '5678901-2345', 'Data Analyst'),
('42661417-bcde-f123-4567-890abcdef1234', 'https://robohash.org/vwx.png', 'Isabella', 'Davis', 'amanda.white@example.com', '1234567-8901', 'Marketing Coordinator'),
('56789012-cdef-1234-5678-7b8c9d0e1f4s', 'https://robohash.org/jkl.png', 'Mason', 'Jones', 'lisa.johnson@example.com', '3456789-0123', 'HR Specialist'),
('abcde123-456f-gh78-90ij-klmnopqrstuv', 'https://robohash.org/jkl.png', 'Mason', 'Jones', 'sarah.jones@example.com', '8765432-1098', 'Sales Associate'),
('qrstuvwx-1234-5678-90ab-cdef12345678', 'https://robohash.org/123.png', 'Olivia', 'Davis', 'kevin.miller@example.com', '8901234-5678', 'Data Analyst'),
('mnopqrst-uvwx-1234-5678-456e7890qws', 'https://robohash.org/abc.png', 'Liam', 'Miller', 'kevin.miller@example.com', '8765432-1098', 'Marketing Coordinator'),
('56ps9012-456f-gh78-90ij-klmnopqrstuv', 'https://robohash.org/ghi.png', 'Olivia', 'Johnson', 'david.brown@example.com', '6543210-9876', 'Software Engineer'),
('456e7890-f12c-34a5-b678-567812345678', 'https://robohash.org/yz.png', 'Sophia', 'Jones', 'john.doe@example.com', '2345678-9012', 'Content Writer'),
('9a0b1c2-cdef-1234-5678-90abcdef123456', 'https://robohash.org/123.png', 'Ava', 'Davis', 'sarah.jones@example.com', '6543210-9876', 'Project Manager'),
('5c6d7e8f-9a0b1c2-3d4e5f6a-7b8c9d0e1f', 'https://robohash.org/stu.png', 'Isabella', 'Wilson', 'david.brown@example.com', '559985-1234', 'Sales Associate');