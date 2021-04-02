package com.ashoka.apiresource;

public final class CreateRoleWithPermission {
	//Uniform Resource Identifier

		public static final String uri = "https://dev-qm4.bifreedom.com/";
		public static final String ResourceURI =  "/v1.0/role/createWtPerm";
		public static final String multipleUserPayload ="[\"Sathish15\",\"Sathish16\",\"Sathish17\"]";
		public static final String permissionPayload ="{\r\n" + 
				"  \"name\": \"AllPermissions\",\r\n" + 
				"  \"descripion\": \"AllPermissions\",\r\n" + 
				"  \"isPlatform\": true,\r\n" + 
				"  \"permissions\": [\r\n" + 
				"    {\r\n" + 
				"      \"permissionId\": 2,\r\n" + 
				"      \"access\": \"1111\"\r\n" + 
				"    },\r\n" + 
				"    {\r\n" + 
				"      \"permissionId\": 3,\r\n" + 
				"      \"access\": \"1111\"\r\n" + 
				"    },\r\n" + 
				"    {\r\n" + 
				"      \"permissionId\": 4,\r\n" + 
				"      \"access\": \"1111\"\r\n" + 
				"    },\r\n" + 
				"    {\r\n" + 
				"      \"permissionId\": 5,\r\n" + 
				"      \"access\": \"1111\"\r\n" + 
				"    },\r\n" + 
				"    {\r\n" + 
				"      \"permissionId\": 6,\r\n" + 
				"      \"access\": \"1111\"\r\n" + 
				"    },\r\n" + 
				"    {\r\n" + 
				"      \"permissionId\": 7,\r\n" + 
				"      \"access\": \"1111\"\r\n" + 
				"    },\r\n" + 
				"    {\r\n" + 
				"      \"permissionId\": 8,\r\n" + 
				"      \"access\": \"1111\"\r\n" + 
				"    },\r\n" + 
				"    {\r\n" + 
				"      \"permissionId\": 9,\r\n" + 
				"      \"access\": \"1111\"\r\n" + 
				"    },\r\n" + 
				"    {\r\n" + 
				"      \"permissionId\": 10,\r\n" + 
				"      \"access\": \"1111\"\r\n" + 
				"    },\r\n" + 
				"    {\r\n" + 
				"      \"permissionId\": 11,\r\n" + 
				"      \"access\": \"1111\"\r\n" + 
				"    },\r\n" + 
				"    {\r\n" + 
				"      \"permissionId\": 12,\r\n" + 
				"      \"access\": \"1111\"\r\n" + 
				"    },\r\n" + 
				"    {\r\n" + 
				"      \"permissionId\": 13,\r\n" + 
				"      \"access\": \"1111\"\r\n" + 
				"    },\r\n" + 
				"    {\r\n" + 
				"      \"permissionId\": 14,\r\n" + 
				"      \"access\": \"1111\"\r\n" + 
				"    },\r\n" + 
				"    {\r\n" + 
				"      \"permissionId\": 15,\r\n" + 
				"      \"access\": \"1111\"\r\n" + 
				"    },\r\n" + 
				"    {\r\n" + 
				"      \"permissionId\": 16,\r\n" + 
				"      \"access\": \"1111\"\r\n" + 
				"    },\r\n" + 
				"    {\r\n" + 
				"      \"permissionId\": 17,\r\n" + 
				"      \"access\": \"1111\"\r\n" + 
				"    },\r\n" + 
				"    {\r\n" + 
				"      \"permissionId\": 18,\r\n" + 
				"      \"access\": \"1111\"\r\n" + 
				"    },\r\n" + 
				"    {\r\n" + 
				"      \"permissionId\": 19,\r\n" + 
				"      \"access\": \"1111\"\r\n" + 
				"    },\r\n" + 
				"    {\r\n" + 
				"      \"permissionId\": 20,\r\n" + 
				"      \"access\": \"1111\"\r\n" + 
				"    },\r\n" + 
				"    {\r\n" + 
				"      \"permissionId\": 22,\r\n" + 
				"      \"access\": \"1111\"\r\n" + 
				"    },\r\n" + 
				"    {\r\n" + 
				"      \"permissionId\": 24,\r\n" + 
				"      \"access\": \"1111\"\r\n" + 
				"    },\r\n" + 
				"    {\r\n" + 
				"      \"permissionId\": 25,\r\n" + 
				"      \"access\": \"1111\"\r\n" + 
				"    },\r\n" + 
				"    {\r\n" + 
				"      \"permissionId\": 26,\r\n" + 
				"      \"access\": \"1111\"\r\n" + 
				"    },\r\n" + 
				"    {\r\n" + 
				"      \"permissionId\": 27,\r\n" + 
				"      \"access\": \"1111\"\r\n" + 
				"    },\r\n" + 
				"    {\r\n" + 
				"      \"permissionId\": 30,\r\n" + 
				"      \"access\": \"1111\"\r\n" + 
				"    },\r\n" + 
				"    {\r\n" + 
				"      \"permissionId\": 32,\r\n" + 
				"      \"access\": \"1111\"\r\n" + 
				"    },\r\n" + 
				"    {\r\n" + 
				"      \"permissionId\": 34,\r\n" + 
				"      \"access\": \"1111\"\r\n" + 
				"    },\r\n" + 
				"    {\r\n" + 
				"      \"permissionId\": 35,\r\n" + 
				"      \"access\": \"1111\"\r\n" + 
				"    },\r\n" + 
				"    {\r\n" + 
				"      \"permissionId\": 36,\r\n" + 
				"      \"access\": \"1111\"\r\n" + 
				"    },\r\n" + 
				"    {\r\n" + 
				"      \"permissionId\": 37,\r\n" + 
				"      \"access\": \"1111\"\r\n" + 
				"    },\r\n" + 
				"    {\r\n" + 
				"      \"permissionId\": 38,\r\n" + 
				"      \"access\": \"1111\"\r\n" + 
				"    },\r\n" + 
				"    {\r\n" + 
				"      \"permissionId\": 43,\r\n" + 
				"      \"access\": \"1111\"\r\n" + 
				"    },\r\n" + 
				"    {\r\n" + 
				"      \"permissionId\": 44,\r\n" + 
				"      \"access\": \"1111\"\r\n" + 
				"    },\r\n" + 
				"    {\r\n" + 
				"      \"permissionId\": 46,\r\n" + 
				"      \"access\": \"1111\"\r\n" + 
				"    }\r\n" + 
				"  ]\r\n" + 
				"}"; 
		public static final String token ="eyJhbGciOiJIUzI1NiJ9.eyJqdGkiOiJTYXRoaXNoIiwiaWF0IjoxNjE1NzM2NzQwLCJpc3MiOiJQaWxsaXIiLCJsb2dpbk5hbWUiOiJTYXRoaXNoIiwiZmlyc3ROYW1lIjoiU2F0aGlzaCIsImxhc3ROYW1lIjoiS3VtYXIiLCJlbWFpbCI6InNhdGhpc2gua3VtYXJAcGlsbGlyLmlvIiwidGVuYW50SWQiOiJTQVQxMDAwMiIsInVzZXJJZCI6MTI2NTMsImNvbXBhbnlJZCI6ODE3LCJkZXZpY2VUeXBlIjoiVW5rbm93biIsInRlbXBUZW5hbnRJZCI6IiIsInRlbXBDb21wYW55SWQiOi0xLCJyb2xlTmFtZSI6IlRlbmFudCBBZG1pbiIsInNhbHV0YXRpb24iOiJNciIsImNvbnRhaW5lckFwcElkIjotMSwidHlwZSI6InR5cGUiLCJpc1Byb3h5TG9naW4iOmZhbHNlLCJleHAiOjE2MTU3NTExNDB9.ubTdWLO7gzr5yB1JZT7CsImOTtAUIQnKW7J5uEMUXSg";
		public static final String propertyPayload="{\r\n" + 
				"    \"properties\":\r\n" + 
				"    [{\r\n" + 
				"      \"name\":\"AllowedLeaves\",\r\n" + 
				"      \"value\":\"[\\\"SickLeave\\\",\\\"CasualLeave\\\"]\"\r\n" + 
				"    },\r\n" + 
				"    {\r\n" + 
				"      \"name\":\"Allowances\",\r\n" + 
				"      \"value\":\"[\\\"GradeA\\\",\\\"GradeB\\\",\\\"GradeC\\\",\\\"GradeD\\\"]\"\r\n" + 
				"    }]\r\n" + 
				"}";
}
