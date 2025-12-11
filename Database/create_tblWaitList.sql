IF NOT EXISTS (SELECT * FROM sys.objects WHERE object_id = OBJECT_ID(N'[dbo].[tblWaitList]') AND type='U')
BEGIN
    CREATE TABLE dbo.tblWaitList (
        waitID VARCHAR(50) NOT NULL PRIMARY KEY,
        userID VARCHAR(50) NOT NULL,
        serviceID VARCHAR(50) NOT NULL,
        appointmentDate VARCHAR(20) NOT NULL,
        status VARCHAR(20) NOT NULL DEFAULT 'waiting',
        CONSTRAINT FK_WaitList_User FOREIGN KEY (userID) REFERENCES dbo.tblUsers(userID),
        CONSTRAINT FK_WaitList_Service FOREIGN KEY (serviceID) REFERENCES dbo.tblServices(serviceID)
    );
END;
