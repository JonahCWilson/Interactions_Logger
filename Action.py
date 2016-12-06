import time

class Action:

	# Constructor
	# Input Type(s): String, String, String
	def  __init__(self, id, type, description):
		self.date = time.strftime("%m/%d/%y")
		self.id = id
		self.type = type
		self.description = description

	# getDate
	# Input: None
	# rType: String
	def getDate(self):
		return self.date

	# setID
	# Input: String
	# rType: void
	def setID(self, id):
		self.id = id

	# getID
        # Input: None
        # rType: String
        def getID(self):
             return self.id

	# setType
	# Input: String
	# rType: void
	def setType(self, type):
		self.type = type

	# getType
	# Input: None
	# rType: String
	def getType(self):
		return self.type

	# setDescription
	# Input: String
	# rType: void
	def setDescription(self, desc):
		self.description = desc

	# getDescription
	# Input: None
	# rType: String
	def getDescription(self):
		return self.description
