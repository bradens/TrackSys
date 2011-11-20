-- Create the notifications table.
CREATE TABLE notifications (id int(11) NOT NULL AUTO_INCREMENT PRIMARY KEY, title varchar(255), message text,date timestamp);

-- Create the bookings table. 					  
CREATE TABLE bookings ( id int(11) AUTO_INCREMENT NOT NULL PRIMARY KEY, clubid int(11) NOT NULL, trackid int(11) NOT NULL, startTime timestamp NOT NULL, endTime timestamp NOT NULL, bookedTime timestamp NOT NULL, comment text, FOREIGN KEY (clubid) REFERENCES club(id));

-- Create the tracks table.
CREATE TABLE tracks ( trackid int(11) NOT NULL AUTO_INCREMENT, isbooked char(1) NOT NULL, PRIMARY KEY(trackid));