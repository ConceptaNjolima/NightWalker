Group Project - README Template
===

# The Nightwalker

## Table of Contents
1. [Overview](#Overview)
1. [Product Spec](#Product-Spec)
1. [Wireframes](#Wireframes)
2. [Schema](#Schema)

## Overview
### Description
The Nightwalker app is a security app that enables users to call 911 emergency line, and communicate your friends and family. It allows security officials and family members to get your location.

# Video Walkthrough
<img src= 'https://github.com/The-NightWalkers/NightWalker/blob/master/demo.gif' style="vertical-align:middle" width="300" title='Nightwalker: Final Version'/>


### App Evaluation
[Evaluation of your app across the following attributes]
- **Category:** Lifestyle & Security
- **Mobile:** Website is interactive and uses a users phone call functionality to contact police and location information.
- **Story:** We want to provide a way for college students around the country to feel safe, even when in unsafe situations.
- **Market:** College Students
- **Habit:** User can immediately call 911 upon opening the app or they can choose to use GPS or ask friends for a walk around campus/the surrounding area. Choosing GPS will allow the user to see their location and send their location to friends.
- **Scope:** The NightWalker will enable users call 911 in case of emergency situations. Users can invite a friend to be a night walker with whom the user shares their current location.

## Product Spec

### 1. User Stories (Required and Optional)

**Required Must-have Stories**

-  [X] Signup and Login 
-  [X] Emergency calls to 911
-  [X] Track Location
-  [ ] Invite a friend as a Night Walker
 

**Optional Nice-to-have Stories**

* OAuth
* Great UI design
* Share location with NighWalker
* Track my location
* Recorded voice instead of calling 911 to send location

## Video Walkthrough
This is the walkthrough of Unit 14 where a location is sent to the Police for tracking

<img src='https://github.com/The-NightWalkers/NightWalker/blob/master/nightWalker.gif' style="vertical-align:middle" width="300" title='Sending Location to Police via SMS'/>

Here's a walkthrough of Unit 12 user story implementation

<img src='https://github.com/The-NightWalkers/NightWalker/blob/Sama-GPS/GPS%20Location.gif' style="vertical-align:middle" width="300" title='Login'/>

Here's a walkthough for Unit 11 user story implementation

<img src='https://github.com/The-NightWalkers/NightWalker/blob/master/Walkthrough_Nightwalker.gif' style="vertical-align:middle" width="300" title='Sign Up'/>

Here's a walkthrough of implemented user stories:

<img src='https://github.com/The-NightWalkers/NightWalker/blob/master/Login.gif' style="vertical-align:middle" width="300" title='Login'/>

<img src='https://github.com/The-NightWalkers/NightWalker/blob/master/Signup.gif' style="vertical-align:middle" width="300" title='Sign Up'/>


### 2. Screen Archetypes

* Login Screen
   -  [X] Sign up for our services 
   -  [X] Login to account 
* Home Screen
   -  [X]  Bottom navigation for GPS and Social
   -  [X]  Alert the authorities button
* Track my location / Social Screen
   -  [X]  Implements Google Map and uses Google Map API
   -  [X]  Shows your current location on the map
   -  [ ]  Has a track location function where friends can track your progress to the destination
   -  [ ]  Invite friends

### 3. Navigation

**Tab Navigation** (Tab to Screen)

* Home
* Track my location
* Social 
 

**Flow Navigation** (Screen to Screen)

* Forced Login/Account Creation
* Home Page with Call 911
* Track my location -> Jumps to Location
* Social -> Jumps to chat

## Wireframes
[The NightWalker App WireFrame]


<img src="https://github.com/The-NightWalkers/NightWalker/blob/master/Wireframe.jpg" width=600>

### [BONUS] Digital Wireframes & Mockups

### [BONUS] Interactive Prototype

## Schema 

### Models
## Model: Users
| **Property Name** | **Type**            | **All tasks*          |
|:----------------- |:------------------- |:--------------------- |
| username          | pointer to username | user's username       |
| profile avatar    | image               | user's profile avatar |
| password          | string              | user's password       |
| Location          | pointer to Location | user's location       |

## Model: Location 
| **Property Name**| **Type**            | **All tasks*
| :----------------| :-------------------| :---------------------|
| Latitude         |    string           | number and direction of latitude|
| Longitude        |    string           | number and direction of longitude |

## Model: Message
| **Property Name**| **Type**            | **All tasks*          |
| :----------------| :-------------------| :---------------------|
|objectId          |  Pointer             | unique id for the current user                       |
| author           | Pointer              | author of the message                       |
| createdAt        | Date/Time            | Date/time message was sent                       |
## FriendsList
| **Property Name** | **Type**       | **All tasks* |
|:----------------- |:--------       |:------------ |
| priority          |number          |The order of priority of the contacted people            |
| phone number      |number          |The phone numbers of all contacts              |
| relationship      |string          |specifies relationship to highly ranked contacts              |

### Networking

| **Screen**      | HTTP VERB   | **Network requests**                   |
|:--------------- | ---         |:-------------------------------------- |
| Login           |POST         | post user's username and password            |
| Home            |POST         | making a call to police               |
| Location        |GET          | reading the user's location                   |
| List of Friends |GET          | getting contacts                           |
| Contact         |POST         | CREATE a new message                   |
| Invite          |GET          | reads and gets user's contact and select |



- [Add list of network requests by screen ]
- [Create basic snippets for each Parse network request]

## POST REQUEST

### GET REQUEST



 public static void makeRequest(Context context) {
    RequestQueue queue = Contacts.newRequestQueue(context);

    Response.Listener responseListener = new Response.Listener<String>() {
        @Override
        public void onResponse(String contact) {
            if (response!=null){
                Log.d("Contact", contact);
                
            }
        }
    };



