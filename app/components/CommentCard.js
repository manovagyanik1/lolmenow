/**
 * Created by madki on 30/06/17.
 */
import React, {Component} from "react";
import {
  StyleSheet,
  View,
  Image,
  Text,
} from "react-native";
import Icon from "react-native-vector-icons/FontAwesome";
import PropType from "prop-types";

const styles = StyleSheet.create({
  container: {
    flexDirection: "row",
    marginBottom: 10,
  },
  profilePic: {
    flex: 0,
    height: 48,
    width: 48,
    borderRadius: 24,
    margin: 12,
  },
  commentContainer: {
    flex: 1,
    flexDirection: "column",
    alignSelf: "stretch"
  },
  name: {
    fontWeight: "bold",
  },
  commentText: {

  },
  likeIcon: {

  },
  iconsContainer: {
    flex: 1,
    flexDirection: "row",
    justifyContent: "flex-start",
    alignItems: "center",
    height: 20,
  }
});

const CommentCard = ({comment}) => {
  return (
    <View style={styles.container}>
      <Image style={styles.profilePic} source={{uri: comment.profile.photoUrl}}/>
      <View style={styles.commentContainer}>
        <Text style={styles.name}>{comment.profile.name}</Text>
        <Text style={styles.commentText}>{comment.text}</Text>
        <View style={styles.iconsContainer}>
          <Icon name="heart" size={10} color="#000" style={styles.likeIcon}/>
        </View>
      </View>
    </View>
  );
};

CommentCard.propTypes = {
  comment: PropType.object.isRequired
};

export default CommentCard;