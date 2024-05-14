import { StatusBar } from "expo-status-bar";
import { StyleSheet, Text, View, Image } from "react-native";
import { Stack } from "expo-router";
import index from ".";

const logo = require("../assets/solidstockLogo.png");

export default function RootLayout() {
  return (
    <View className="bg-slate-700 flex-1">
      <StatusBar style="auto" />
      <View className="w-full flex justify-center">
        <Image className="mx-auto my-[15vh]" source={logo} />
      </View>
      <View className="w-4/5 h-[50vh] mx-auto fill-slate-700">
        <Stack>
          <Stack.Screen name="index" options={{ headerShown: false }} />
          <Stack.Screen name="register" options={{ headerShown: false }} />
          <Stack.Screen
            name="forgetPassword"
            options={{ headerShown: false }}
          />
        </Stack>
      </View>
    </View>
  );
}
