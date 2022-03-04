
getDtServiceUrl = function (buddyName){
    var uidHost = buddyName.split(";");
    return  "https://"+ uidHost[1] + "/dtservice/desktopcallentry/" + uidHost[0].split(",")[0];
}
console.log(getDtServiceUrl("0EBA588000010000000003628C0B5B0A,FIL-15-1;10.91.11.29:11080"));