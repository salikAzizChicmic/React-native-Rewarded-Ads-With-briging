import React from 'react'
import { Image, Text,View,Button, NativeModules } from 'react-native'


const Login = () => {
    var OpenActivity = NativeModules.OpenActivity;
    const showToast = () =>{
        OpenActivity.open();
       console.log("Hello")
      }
  return (

    <View style={{height:'100%',width:'100%'}}>
    <Button onPress={showToast} title= "Clike Me" />
      <View style={{flexDirection:'row'}}>
            <Text style={{fontSize:27,color:'black',fontWeight:'bold',marginHorizontal:19,marginTop:50}}>{`Google\nVideo Ad\nExample?`}</Text>
            <Image style={{height:200,width:230,objectFit:'fill'}} source={require('../Assets/Login.png')} />
      </View>
  </View>
  )
}

export default Login