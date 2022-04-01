# My Personal Project (Hansol Yang)

## My Favourite Sports Team

###General Description on Project:

- Create a team page of users' ***favourite sports team***
- Add ***match day*** on calendar
- In ***'Add Match'*** page, user can add below:
   - Date and Time
   - Opposing Team
   - Whether the user goes to stadium or not
   - Result
   - Short impression on the match

###Expected User:

- Sports fans who want to check and record about the matches
- Sports fans who want to see when did they book tickets

###Reason I am interested in this project:

- I am a sports fan
- In my experience, I need such application recording the match days easily

###User Stories:

- As a user, I want to be able to create a new team and add it to a list of teams
- As a user, I want to be able to select a team and check the dates with match for the team
- As a user, I want to be able to select a day and add match day information
- As a user, I want to be able to select a day and check the recorded match
- As a user, I want to be able to list all the booked matches on the team
- As a user, I want to be able to save my teams and matches to file
- As a user, I want to be able to load my teams and matches from file even after quit the program and re-execute it

###Phase 4: Task 2

- Example: Adding Match to Team<br />
  Thu Mar 31 17:29:34 PDT 2022<br />
  Added a match on 2022-01-05 of Team B
- Example: Adding Team to TeamList<br />
  Thu Mar 31 17:29:34 PDT 2022<br />
  Added a Team B
- Example: Checking Match added to Team<br />
  Thu Mar 31 17:30:10 PDT 2022<br />
  Checked match on 2022-03-05 of Team B
- Example: Checking all booked matches in Team<br />
  Fri Apr 01 01:23:40 PDT 2022<br />
  Checked all booked matches of Team A

###Phase 4: Task 3
- If I had more time, I would like to revise Match, MatchList, Team, and TeamList to composite pattern.<br />
  TeamList can be component, Team be Composite, and Match be Leaf and MatchList can just be replaced as 
  Collection<Match> in Team.
- If I had more time, I would like to make UI abstract class for MainControllerUI, TeamSelectUI.<br />
  These two share similar data and behaviours, such as adding buttons. It will be more clear<br />
  and have less duplication to make UI abstract class to be extended by MaincontrollerUI and TeamSelectUI.