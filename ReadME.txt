Tunku Abdul Rahman University of Management and Technology Management System Readme
-----------------------------------------------------------------------------------
Introduction
Tunku Abdul Rahman University of Management and Technology University Management System is a comprehensive software solution designed to streamline and automate various administrative and academic processes within a university or educational institution. The main purpose of  Tunku Abdul Rahman University of Management and Technology University is to increase the efficiency, accuracy and transparency of administrative and academic operations within the University. Tunku Abdul Rahman University of Management and Technology University Management System consists of five subsystems which are Student Registration Management Subsystem, Course Management Subsystem, Tutorial Group Management Subsystem, Teaching Assignment Subsystem and Assignment Team Management Subsystem

Usage
1. Please make sure that all files in the folder adt, boundary, control, dao, entity and utility have been downloaded to your devices. 
2. To initiate the system, you are required to navigate to the control/Main.java file and run the program
3. A menu will pop up and you can navigate to 5 different subsystems by input a number. 


Subsystem Specific Instructions
Student Registration Management Subsystem
To access the subsystem's functions, a menu will appear where you can navigate to eleven different options by inputting a number from 1 to 11. To return to the main system, input 0.
1.Display Student List:
	- Choosing this option will display the list of students.
	- Press Enter to return to the subsystem menu.

2. Add New Student:
	- Input the new student's name, gender, phone number, email, and program code to add them to the system.
	- Ensure the program code corresponds to the displayed program list.
	- If invalid data is entered, an error message will prompt you to re-enter the information.
	- Upon successfully adding a student, you'll be prompted to continue adding more students or not. If you choose "no," you'll return to the subsystem menu.

3. Remove Student
	- Input the student's ID.
	- Ensure the ID corresponds to the displayed student list.
	- If the ID is invalid, display a "Student not Found" message.
	- If valid, display a "Successfully removed student" message.
	- Press Enter to return to the subsystem menu.
   
4. Edit Student Details
	- Input the student's ID.
	- Confirm that the ID matches the displayed student list.
	- A criteria menu with six options will appear. Input a number from 1 to 6 to choose the student detail you wish to edit.
	- To return to the subsystem menu, input 0.
	- After selecting a criterion, the existing details will be displayed, and you'll be prompted to input new details.
	- Upon successful modification, a confirmation message will be displayed, and you'll be returned to the criteria menu.

5. Search Students for Registered Courses
	- Input the student's ID.
	- Confirm that the ID matches the displayed student list.
	- Input the course code.
	- Confirm that the course code matches the displayed course list.
	- If the student has enrolled in the specified course, a message will indicate that the student has taken the course along with their enrollment status. Otherwise, a message will state that the student has not taken the course.
	- You'll be prompted to continue searching for students registered in courses or not. If you choose "no," you'll return to the subsystem menu.

6. Add Student to Courses
	- Input a student ID.
	- Confirm that the ID matches the displayed student list.
	- Input the course code.
	- Confirm that the course code matches the displayed course list.
	- If the course code entered by the user does not match the student's program, display an error message and prompt the user to enter the course code again.
	- Prompt the user to input the enrollment status.
	- The student will be added to the courses after valid input is inserted.
	- Prompt the user to enter whether they want to add student to the course again or not. If you choose "no," you'll return to the subsystem menu.

7. Remove Student from Courses
	- Input a student ID.
	- Confirm that the ID matches the displayed student list.
	- Input the course code.
	- Confirm that the course code matches the displayed course list.
	- Input the enrollment status.
	- The student will be removed from the courses after valid input is inserted.
	- Press Enter to return to the subsystem menu.

8. Calculation of Registration Course Fees
	- Input a student ID.
	- Confirm that the ID matches the displayed student list.
	- Display the student's name, student ID, program code, and student bill.
	- Prompt the user to enter whether they want to calculate the registration course fees again or not. If you choose "no," you'll return to the subsystem menu.

9. Filter Student
	- A criteria menu with 3 options will appear. Input a number from 1 to 3 to choose the student detail you wish to filter.
	- To return to the subsystem menu, input 0.
	- If you choose 1 (age), input the maximum and minimum age. The student list will display only students whose age is between the minimum and maximum 	- age.
	- If you choose 2 (gender), input the gender you want to filter by. The student list will display only students of that gender.
	- If you choose 3 (programme), a program list will be displayed. Input a program code, and the student list will display only students enrolled in that program.
	- After the filtered student list is displayed, press Enter to return to the criteria menu.

10. Sort Student
	- A student detail menu with 7 options will appear. Input a number from 1 to 7 to choose the student detail you wish to sort.
	- To return to the subsystem menu, input 0.
	- After choosing the student detail, you'll be prompted to choose either ascending or descending order.
	- The sorted student list will be displayed according to the chosen detail and order.
	- After the sorted student list is displayed, press Enter to return to the student detail menu.

11. Summary Report
	- Choose the type of summary report by inputting 1 or 2.
	- To return to the subsystem menu, input 0.
	- After the summary report is shown, press Enter to return to the summary report menu.


Course Management Subsystem
To access the subsystem's functions, a menu will appear where you can navigate to night different options by inputting a number from 1 to 9. To return to the main system, input 0.
1. Add a programme to courses
	- Input the course code and the programme code
	- Ensure the course code and the program code corresponds to the displayed course list and programme list.
	- If the programme has already taken the course, an error message will be displayed. Else, vice versa.
	- Press Enter to return to the subsystem menu.

2. Remove programme from course
	- Input the programme code and the course code
	- Ensure the course code and the program code corresponds to the displayed course list and programme list.
	- If the user intends to remove a course that is not taken by the programme, an error message will be displayed.
	- Once the user confirms to remove, a successful message will be displayed. Else, the removal process is canceled.
	- Press Enter to return to the subsystem menu.

3. Add new course to Programme
	- Input the new course code, course name, credit hour and semester.
	- If the course already exists in the system, an error message will be displayed. Else, the system will proceed to prompt users to input programme code.
	- Once the user input the valid programme code, the system will display the successful message. Else, vice versa.
	- Press Enter to return to the subsystem menu.

4. Remove course from programme
	- Input the programme code and the course code
	- Ensure the course code and the program code corresponds to the displayed course list and programme list.
	- If the user intends to remove a course that is not taken by the programme, an error message will be displayed.
	- Once the user confirms to remove, a successful message will be displayed. Else, the removal process is canceled.
	- Press Enter to return to the subsystem menu.

5. Search courses offered in a semester
	- Input the semester and the system will display the corresponding courses.
	- Press Enter to return to the subsystem menu.

6. Amend course details
	- Input the course code
	- Ensure the course code is valid.
	- A menu of course attributes will be displayed and prompt the user to choose.
	- After selecting a criterion, the existing details will be displayed, and you'll be prompted to input new details.
	- Upon successful modification, a confirmation message will be displayed, and you'll be returned to the criteria menu.
	- To return to the subsystem menu, input 0 and then press Enter to return to the subsystem menu.

7. List all courses taken by faculty
	- Input the faculty code
	- Ensure the faculty code corresponds to the displayed faculty list.
	- Display the course information taken by the faculty
	- Press Enter to return to the subsystem menu.

8. List all courses for a programme
	- Input the programme code
	- Ensure the programme code corresponds to the displayed programme list.
	- Display the course information taken by the programme
	- Press Enter to return to the subsystem menu.

10. Summary report
	- A summary report menu will be displayed and prompt users to choose
	- Choose the type of summary report by inputting 1, 2 or 3.
	- Then a sorting menu will be displayed for the user to choose which attribute and the order to sort.
	- To return to the subsystem menu, input 0.
	- After the summary report is shown, press Enter to return to the summary report menu.


Tutorial Group Management Subsystem
To access the subsystem's functions, a menu will appear where you can navigate to night different options by inputting a number from 1 to 9. To return to the main system, input 0.
1. Add a tutorial group to a programme
	- Input the programme code to add a new tutorial group.
	- System will auto assign tutorial group id and tutorial group name and display it.

2. Remove a tutorial group from a programme
	- Input the programme code and it will show all tutorial groups that are under the same programme code. Then ask the user whether they want to remove the tutorial group.
	- If yes, then require the user to input tutorial group id.
	- If no, return to the subsystem menu.

3. List All Tutorial Group
	- Input the programme code then it will list all tutorial groups under that programme code.

4. Add new student to a tutorial group
	- Input the programme code and then input the tutorial group.Next, input the studentID that you want to add inside the tutorial group.
	- Multiple student add inside a tutorial group at same time is allowed just require separate studentID by comma(,) and avoid spacing.
	- Students should be under the same programme code.
	- One student only can be assigned to one tutorial group.

5. Remove a student from tutorial group
	- Input the programme code and then input the tutorial group.Next, input the studentID that you want to remove from the tutorial group.

6. Change the tutorial group for a student
	- Input the programme code and then input the current tutorial groupID of the student .Next, input the studentID. Lastly, input the tutorial groupID that the student wants to change.

7. List all students in a tutorial group and a programme
	- Input the programme code and it will ask the user whether they want to view all students that are under the same programme code or not.
	- If yes then display all students that are under the programme. Then display a list of the tutorial groups under the programme.
	- Input the tutorial group ID then display all students that are under that tutorial group.

8. Merge two tutorial groups become one tutorial group
	- Input the programme code and input two tutorial group ID separate by comma(,)
	- If one of the tutorial groups already has more than five students then cannot merge the tutorial group with another.

9. Summary report
	- Choose the type of summary report by inputting 1 or 2.
	- To return to the subsystem menu, input 0.
	- After the summary report is shown, press Enter to return to the summary report menu.


Teaching Assignment Subsystem
To access the subsystem's functions, a menu will appear where you can navigate to night different options by inputting a number from 1 to 9. To return to the main system, input 0.
1. Assign a tutor to a course
	- Input the course code and tutor id
	- The system will assign the tutor to the course chosen

2. Add a tutorial group to a tutor
	- Input the tutor id and tutorial group
	- The system will add the tutorial group to the tutor chosen

3. Add a tutor to a tutorial group for a course
	- Input course code, the system will display tutorial groups which are taking the course chosen.
	- Input tutorial group id, the system will then display the tutors who are teaching the course.
	- Input tutor id, the system will add the tutor to the tutorial group for the course.

4. Search courses under a tutor
	- Input tutor id, the system will display the courses under the tutor.
	- Search tutors under a course
	- Input course code, the system will display the tutors under the course.

5. List all tutors and tutorial groups under a course
	- Input course code, the system will display the tutor and tutorial group list for the course.

6. List all courses for each tutor
	- The system will display all tutors and their courses together with their tutor types [L, T, P].

7. Filter tutor based on criteria
	- The system will display a tutor list and criteria menu
	- Input criteria to filter tutor (1. Age Above   2. Age Below   3. Gender)
	- When choosing 1, input age and the system will display tutors who are above the age.
	- When choosing 2, input age and the system will display tutors who are below the age.
	- When choosing 3, input gender and the system will display tutors who belongs to that gender (either male or female).
	- To return to the main menu, input 0.

8. Summary report
	- Choose the type of summary report by inputting 1 or 2.
	- To return to the subsystem menu, input 0.
	- After the summary report is shown, press Enter to return to the summary report menu.


Assignment Team Management Subsystem

1. Create Assignment Team for Tutorial Group
	- Input the programme code and tutorial group to choose a group.
	- Enter the number of teams created and the size of the assignment team.
	- System will auto assign assignment team id and assignment team name for new teams.
	- Updated assignment team list will be shown.

2. Remove Assignment Team from Tutorial Group
	- Input the programme code and tutorial group to choose a group.
	- Input the assignment team ID to remove the group.

3. Amend Assignment Team Details
	- Input the programme code and tutorial group to choose a group.
	- Input the assignment team ID to edit the group.
	- Three edit options will be displayed, enter number  1-3 to select.
	- After selecting a criterion, the existing details will be displayed, and you'll be prompted to input new details.
	- Team size cannot be changed to value smaller than the current existing member.
	- Team leaders cannot be assigned to students not in the group.
	- Press Enter to return to the subsystem menu.

4. Add Students to Assignment Team
	- Input the programme code and tutorial group to choose a group.
	- Input the assignment team ID.
	- Enter 0 to display the student details in this tutorial group.
	- Input student ID to add the student into the assignment team.
	- Fails when the user enters a student not in the selected tutorial group or student already in the other assignment team.
	- Fails when the assignment team is full.

5. Remove Students from Assignment Team
	- Input the programme code and tutorial group to choose a group.
	- Input the assignment team ID.
	- List of team members will be displayed.
	- Enter student ID to remove the student from the group.

6. Merge Assignment Team based on criteria
	- Input the programme code and tutorial group to choose a group.
	- Input the assignment team ID.
	- Enter the first assignment team ID to merge.
	- List of  suitable teams that can merge with the first team selected will be displayed. (Suitable team is calculated by number of first team member add second team member smaller or equal to the first team team size) 
	- Enter the second assignment team ID.

7. List Assignment Teams for a Tutorial Group
	- Input the programme code and tutorial group to choose a group.
	- List of assignment teams under the group will be displayed.

8. List Students Under an Assignment Team
	- Input the programme code and tutorial group to choose a group.
	- Input the assignment team ID.
	- List of assignment team members under the team will be displayed.

9.Summary report
	- Choose the type of summary report by inputting 1 or 2.
	- To return to the subsystem menu, input 0.
	- After the summary report is shown, press Enter to return to the summary report menu.


Contributors
Tan Lock Kwan, Loh Jia Shou, Goh Boon Xiang, Lim Hoi Yau, Tan Hoong Guan 
