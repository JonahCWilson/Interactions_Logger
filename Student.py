class Student:
	
	# Constructor
	# input: String, String, String, Integer
	def __init__(self, id, name, school, grade):
		self.id = id
		self.name = name
		self.school = school
		self.grade = grade

		#Actions Initialization
		self.actions = []
	
	# setID
	# input: String
	# rType: void
	def setID(self, id):
		self.id = id
	
	# getID
	# input: None
	# rType: String
	def getID(self):
		return self.id

	# setName
	# input: String
	# rType: void
	def setName(self, name):
		self.name = name

	# getName
	# input: None
	# rType: String
	def getName(self):
		return self.name

	# setSchool
	# input: String
	# rType: void
	def setSchool(self, school):
		self.school = school

	# getSchool
	# input: None
	# rType: String
	def getSchool(self):
		return self.school

	# setGrade
	# input: Integer (-1 - 12)
	# rType: void
	def setGrade(self, grade):
		self.grade = grade

	# getGrade
	# input: None
	# rType: Integer
	def getGrade(self):
		return self.grade


	# addAction
	# input: Action
	# rType: void
	def addAction(self, action):
		self.actions.append(action)

	# getActions
	# input: None
	# rType: List of Actions
	def getActions(self):
		return self.actions
