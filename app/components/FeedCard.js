/**
 * Created by madki on 29/06/17.
 */
import React, {Component} from "react";
import PropType from "prop-types";
import {
  View,
  Image,
  Text,
  StyleSheet,
} from "react-native";

const styles = StyleSheet.create({
  container: {
    flex: 1,
    marginBottom: 15,
    elevation: 4,
    backgroundColor: "#FFFFFF",
    shadowColor: "black",
    shadowOpacity: 0.1,
    shadowRadius: 4,
    shadowOffset: {
      width: 0,
      height: 4
    }
  },
  image: {
    flex: 1,
    width: undefined,
    height: 200,
  },
  cardFooter: {
    flex: 1,
    flexDirection: "row",
  },
});

const FeedCard = ({card}) => {
  const {data} = card;
  return (
    <View style={styles.container}>
      <Image
        source={{uri: data.mediaUrl}}
        style={styles.image}
        defaultSource={require("../img/placeholder.jpg")}>
      </Image>
    </View>
  );
};

FeedCard.propTypes = {
  card: PropType.object.isRequired
};

export default FeedCard;

