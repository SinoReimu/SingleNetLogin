Include EncryptPassword(java) loginshanxun.sh(shell)


Login process :
->click login:
  network Certification 
  netWorkAvailable() check wifi isActive?
  
  Username = Original Text
  Password = AESEncrypt.encrypt(LogonActivity.this.md5("7%ChIna3#@Net*%"), 'Original Password', true);

  this.mLogonManager.startLogon(LogonActivity.this.et_username(Original Username).getText().toString().trim(), str2 (ENcryptPassword), LogonActivity.this.getIP());

->PasswordEncypt:
  Aes.encrypt(md5("7%ChIna3#@Net*%"),password)->toHex()->return

->GetIP:
   getIPAddress()

->startLogon:
   username and encrypt password trim and add to a sync task

->LogonSyncTask:
   getAloginUrl from a document downloaded from the got IP
   $.post(url='SETSHOWLOGON'){
     wlanuserip:IP, gotip
   } 
   Then ansyale it
   getElement <LoginURL>
       Constant.setLogonUrl(url);
       localLogonTask1.execute(url);
   getELement <UserIP> <Uuid>
       localLogonTask2.setUserip(ip);
       localLogonTask2.setUuid(uuid);
          localLogonTask2.setUsername(username);
          localLogonTask2.setPassword(password);
          localLogonTask2.setRatingtype(1+"");
          String[] arrayOfString2 = new String[1];
          arrayOfString2[0] = Constant.URL_LOGON;
          localLogonTask2.execute(arrayOfString2);

->LogonTask
      doHttpPost(SHOW_LOGON_URI, localArrayList{uuid,userip,username,password,ratingtype});
      getLogoffURL  logoffuuid  logoffuserIP
