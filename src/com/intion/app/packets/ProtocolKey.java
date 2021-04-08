//自动生成，请不要自行修改!!!!!!!!!!!!!!!

package com.intion.app.packets;

import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;

import com.google.protobuf.Message;
import com.intion.app.packets.Family.*;
import com.intion.app.packets.Fight.*;
import com.intion.app.packets.User.*;

/**
 * 这个是所有的协议编号定义类，通过工具生成。
 * 具体就是写个工具，读取.proto文件中所有的协议，自动生成一堆编号
 */
public class ProtocolKey {

	public static Map<Short, MessageAndId> messageMap = new HashMap<>();

	public static final short GetFamilyInfo = 5001;
	public static final short GetFamilyInfoBack = 5001;
	public static final short GetFamilyRecommend = 5002;
	public static final short GetFamilyRecommendBack = 5002;
	public static final short CreateFamily = 5003;
	public static final short CreateFamilyBack = 5003;
	public static final short JoinFamily = 5004;
	public static final short JoinFamilyBack = 5004;
	public static final short QuitFamily = 5005;
	public static final short QuitFamilyBack = 5005;
	public static final short AgreeJoinFamily = 5006;
	public static final short AgreeJoinFamilyBack = 5006;
	public static final short DisagreeJoinFamily = 5007;
	public static final short DisagreeJoinFamilyBack = 5007;
	public static final short SetJoinWay = 5008;
	public static final short SetJoinWayBack = 5008;
	public static final short SetNotice = 5009;
	public static final short SetNoticeBack = 5009;
	public static final short SetFamilyName = 5010;
	public static final short SetFamilyNameBack = 5010;
	public static final short GivePost = 5011;
	public static final short GivePostBack = 5011;
	public static final short ImpeachCorpsLeader = 5012;
	public static final short ImpeachCorpsLeaderBack = 5012;
	public static final short KickMember = 5013;
	public static final short KickMemberBack = 5013;
	public static final short GetFamilyFightSimpleInfo = 5014;
	public static final short GetFamilyFightSimpleInfoBack = 5014;
	public static final short ChooseStartPoint = 5015;
	public static final short ChooseStartPointBack = 5015;
	public static final short GetFamilyFightPlayerInfo = 5016;
	public static final short GetFamilyFightPlayerInfoBack = 5016;
	public static final short GetFamilyFightCastleInfo = 5017;
	public static final short GetFamilyFightCastleInfoBack = 5017;
	public static final short FamilyFightMove = 5018;
	public static final short FamilyFightMoveBack = 5018;
	public static final short CloseFFMessage = 5019;
	public static final short CloseFFMessageBack = 5019;
	public static final short ChooseYourOperation = 5020;
	public static final short ChooseYourOperationBack = 5020;
	public static final short FightMatch = 4001;
	public static final short FightMatchBack = 4001;
	public static final short UploadTeam = 4002;
	public static final short UploadTeamBack = 4002;
	public static final short Operation = 4003;
	public static final short OperationBack = 4003;
	public static final short CloseFight = 4004;
	public static final short CloseFightBack = 4004;
	public static final short PvpAgreeFightIn = 4005;
	public static final short PvpAgreeFightInBack = 4005;
	public static final short PvpDisagreeFightIn = 4006;
	public static final short PvpDisagreeFightInBack = 4006;
	public static final short PvpFightMathOver = 4007;
	public static final short PvpFightMathOverBack = 4007;
	public static final short Sync = 3001;
	public static final short UpdateMail = 3002;
	public static final short UpdateRequestFriend = 3003;
	public static final short Close = 3004;
	public static final short FightStart = 3005;
	public static final short UpdateChatMessage = 3006;
	public static final short UpdateTask = 3007;
	public static final short UpdateFamilyFightSimpleInfo = 3008;
	public static final short PvpHadMatchPlayer = 3009;
	public static final short PvpFightStart = 3010;
	public static final short PvpBeChoose = 3011;
	public static final short PvpUploadTeam = 3012;
	public static final short UpdateChooseYourOperation = 3013;
	public static final short FamilyFightStart = 3014;
	public static final short Login = 1001;
	public static final short LoginBack = 1001;
	public static final short CreateRole = 1002;
	public static final short CreateRoleBack = 1002;
	public static final short GetMailList = 1003;
	public static final short GetMailListBack = 1003;
	public static final short ReadMailReward = 1004;
	public static final short ReadMailRewardBack = 1004;
	public static final short ReceiveMailReward = 1005;
	public static final short ReceiveMailRewardBack = 1005;
	public static final short DeleteMail = 1006;
	public static final short DeleteMailBack = 1006;
	public static final short GetRecommendFriends = 1007;
	public static final short GetRecommendFriendsBack = 1007;
	public static final short GetMyFriends = 1008;
	public static final short GetMyFriendsBack = 1008;
	public static final short GetFriendRequestList = 1009;
	public static final short GetFriendRequestListBack = 1009;
	public static final short SendGift = 1010;
	public static final short SendGiftBack = 1010;
	public static final short ReceiveGift = 1011;
	public static final short ReceiveGiftBack = 1011;
	public static final short FindUser = 1012;
	public static final short FindUserBack = 1012;
	public static final short RequestFriend = 1013;
	public static final short RequestFriendBack = 1013;
	public static final short AgreeFriendRequest = 1014;
	public static final short AgreeFriendRequestBack = 1014;
	public static final short RejectRequestFriend = 1015;
	public static final short RejectRequestFriendBack = 1015;
	public static final short RemoveFriend = 1016;
	public static final short RemoveFriendBack = 1016;
	public static final short GetBlackList = 1017;
	public static final short GetBlackListBack = 1017;
	public static final short AddBlack = 1018;
	public static final short AddBlackBack = 1018;
	public static final short RemoveBlack = 1019;
	public static final short RemoveBlackBack = 1019;
	public static final short HeartBeat = 1020;
	public static final short HeartBeatBack = 1020;
	public static final short F9 = 1021;
	public static final short F9Back = 1021;
	public static final short Treasure = 1022;
	public static final short TreasureBack = 1022;
	public static final short HeroLevelUp = 1023;
	public static final short HeroLevelUpBack = 1023;
	public static final short StarUp = 1024;
	public static final short StarUpBack = 1024;
	public static final short UpgradeTitle = 1025;
	public static final short UpgradeTitleBack = 1025;
	public static final short SkillLevelUp = 1026;
	public static final short SkillLevelUpBack = 1026;
	public static final short EquipEquip = 1027;
	public static final short EquipEquipBack = 1027;
	public static final short EquipUnInstall = 1028;
	public static final short EquipUnInstallBack = 1028;
	public static final short BreakUpHero = 1029;
	public static final short BreakUpHeroBack = 1029;
	public static final short EquipLevelUp = 1030;
	public static final short EquipLevelUpBack = 1030;
	public static final short EnhanceEquip = 1031;
	public static final short EnhanceEquipBack = 1031;
	public static final short EquipQualityUp = 1032;
	public static final short EquipQualityUpBack = 1032;
	public static final short EquipCreate = 1033;
	public static final short EquipCreateBack = 1033;
	public static final short TransUserEquip = 1034;
	public static final short TransUserEquipBack = 1034;
	public static final short BreakUpEquip = 1035;
	public static final short BreakUpEquipBack = 1035;
	public static final short GetShopData = 1036;
	public static final short GetShopDataBack = 1036;
	public static final short FlushUserShop = 1037;
	public static final short FlushUserShopBack = 1037;
	public static final short ShopBuy = 1038;
	public static final short ShopBuyBack = 1038;
	public static final short GetChatMessage = 1039;
	public static final short GetChatMessageBack = 1039;
	public static final short ChatMessage = 1040;
	public static final short ChatMessageBack = 1040;
	public static final short GetRank = 1041;
	public static final short GetRankBack = 1041;
	public static final short GetTask = 1042;
	public static final short GetTaskBack = 1042;
	public static final short GetTaskReward = 1043;
	public static final short GetTaskRewardBack = 1043;
	public static final short GetDailyTrialInfo = 1044;
	public static final short GetDailyTrialInfoBack = 1044;
	public static final short SweepDailyTrial = 1045;
	public static final short SweepDailyTrialBack = 1045;
	public static final short GetGateInfo = 1046;
	public static final short GetGateInfoBack = 1046;

	static {

		messageMap.put(StarUp, new MessageAndId("starUp", starUp.getDefaultInstance()));
		messageMap.put(UpgradeTitle, new MessageAndId("upgradeTitle", upgradeTitle.getDefaultInstance()));
		messageMap.put(SkillLevelUp, new MessageAndId("skillLevelUp", skillLevelUp.getDefaultInstance()));
		messageMap.put(EquipEquip, new MessageAndId("equipEquip", equipEquip.getDefaultInstance()));
		messageMap.put(EquipUnInstall, new MessageAndId("equipUnInstall", equipUnInstall.getDefaultInstance()));
		messageMap.put(BreakUpHero, new MessageAndId("breakUpHero", breakUpHero.getDefaultInstance()));
		messageMap.put(EquipLevelUp, new MessageAndId("equipLevelUp", equipLevelUp.getDefaultInstance()));
		messageMap.put(EnhanceEquip, new MessageAndId("enhanceEquip", enhanceEquip.getDefaultInstance()));
		messageMap.put(EquipQualityUp, new MessageAndId("equipQualityUp", equipQualityUp.getDefaultInstance()));
		messageMap.put(EquipCreate, new MessageAndId("equipCreate", equipCreate.getDefaultInstance()));
		messageMap.put(TransUserEquip, new MessageAndId("transUserEquip", transUserEquip.getDefaultInstance()));
		messageMap.put(BreakUpEquip, new MessageAndId("breakUpEquip", breakUpEquip.getDefaultInstance()));
		messageMap.put(GetShopData, new MessageAndId("getShopData", getShopData.getDefaultInstance()));
		messageMap.put(FlushUserShop, new MessageAndId("flushUserShop", flushUserShop.getDefaultInstance()));
		messageMap.put(ShopBuy, new MessageAndId("shopBuy", shopBuy.getDefaultInstance()));
		messageMap.put(GetChatMessage, new MessageAndId("getChatMessage", getChatMessage.getDefaultInstance()));
		messageMap.put(ChatMessage, new MessageAndId("chatMessage", chatMessage.getDefaultInstance()));
		messageMap.put(GetRank, new MessageAndId("getRank", getRank.getDefaultInstance()));
		messageMap.put(GetTask, new MessageAndId("getTask", getTask.getDefaultInstance()));
		messageMap.put(GetTaskReward, new MessageAndId("getTaskReward", getTaskReward.getDefaultInstance()));
		messageMap.put(GetDailyTrialInfo, new MessageAndId("getDailyTrialInfo", getDailyTrialInfo.getDefaultInstance()));
		messageMap.put(SweepDailyTrial, new MessageAndId("sweepDailyTrial", sweepDailyTrial.getDefaultInstance()));
		messageMap.put(GetGateInfo, new MessageAndId("getGateInfo", getGateInfo.getDefaultInstance()));
		messageMap.put(GetFamilyInfo, new MessageAndId("getFamilyInfo", getFamilyInfo.getDefaultInstance()));
		messageMap.put(GetFamilyRecommend, new MessageAndId("getFamilyRecommend", getFamilyRecommend.getDefaultInstance()));
		messageMap.put(CreateFamily, new MessageAndId("createFamily", createFamily.getDefaultInstance()));
		messageMap.put(JoinFamily, new MessageAndId("joinFamily", joinFamily.getDefaultInstance()));
		messageMap.put(QuitFamily, new MessageAndId("quitFamily", quitFamily.getDefaultInstance()));
		messageMap.put(AgreeJoinFamily, new MessageAndId("agreeJoinFamily", agreeJoinFamily.getDefaultInstance()));
		messageMap.put(DisagreeJoinFamily, new MessageAndId("disagreeJoinFamily", disagreeJoinFamily.getDefaultInstance()));
		messageMap.put(SetJoinWay, new MessageAndId("setJoinWay", setJoinWay.getDefaultInstance()));
		messageMap.put(SetNotice, new MessageAndId("setNotice", setNotice.getDefaultInstance()));
		messageMap.put(SetFamilyName, new MessageAndId("setFamilyName", setFamilyName.getDefaultInstance()));
		messageMap.put(GivePost, new MessageAndId("givePost", givePost.getDefaultInstance()));
		messageMap.put(ImpeachCorpsLeader, new MessageAndId("impeachCorpsLeader", impeachCorpsLeader.getDefaultInstance()));
		messageMap.put(KickMember, new MessageAndId("kickMember", kickMember.getDefaultInstance()));
		messageMap.put(GetFamilyFightSimpleInfo, new MessageAndId("getFamilyFightSimpleInfo", getFamilyFightSimpleInfo.getDefaultInstance()));
		messageMap.put(ChooseStartPoint, new MessageAndId("chooseStartPoint", chooseStartPoint.getDefaultInstance()));
		messageMap.put(GetFamilyFightPlayerInfo, new MessageAndId("getFamilyFightPlayerInfo", getFamilyFightPlayerInfo.getDefaultInstance()));
		messageMap.put(GetFamilyFightCastleInfo, new MessageAndId("getFamilyFightCastleInfo", getFamilyFightCastleInfo.getDefaultInstance()));
		messageMap.put(FamilyFightMove, new MessageAndId("familyFightMove", familyFightMove.getDefaultInstance()));
		messageMap.put(CloseFFMessage, new MessageAndId("closeFFMessage", closeFFMessage.getDefaultInstance()));
		messageMap.put(ChooseYourOperation, new MessageAndId("chooseYourOperation", chooseYourOperation.getDefaultInstance()));
		messageMap.put(FightMatch, new MessageAndId("fightMatch", fightMatch.getDefaultInstance()));
		messageMap.put(UploadTeam, new MessageAndId("uploadTeam", uploadTeam.getDefaultInstance()));
		messageMap.put(Operation, new MessageAndId("operation", operation.getDefaultInstance()));
		messageMap.put(CloseFight, new MessageAndId("closeFight", closeFight.getDefaultInstance()));
		messageMap.put(PvpAgreeFightIn, new MessageAndId("pvpAgreeFightIn", pvpAgreeFightIn.getDefaultInstance()));
		messageMap.put(PvpDisagreeFightIn, new MessageAndId("pvpDisagreeFightIn", pvpDisagreeFightIn.getDefaultInstance()));
		messageMap.put(PvpFightMathOver, new MessageAndId("pvpFightMathOver", pvpFightMathOver.getDefaultInstance()));
		messageMap.put(Sync, new MessageAndId("sync", null));
		messageMap.put(UpdateMail, new MessageAndId("updateMail", null));
		messageMap.put(UpdateRequestFriend, new MessageAndId("updateRequestFriend", null));
		messageMap.put(Close, new MessageAndId("close", null));
		messageMap.put(FightStart, new MessageAndId("fightStart", null));
		messageMap.put(UpdateChatMessage, new MessageAndId("updateChatMessage", null));
		messageMap.put(UpdateTask, new MessageAndId("updateTask", null));
		messageMap.put(UpdateFamilyFightSimpleInfo, new MessageAndId("updateFamilyFightSimpleInfo", null));
		messageMap.put(PvpHadMatchPlayer, new MessageAndId("pvpHadMatchPlayer", null));
		messageMap.put(PvpFightStart, new MessageAndId("pvpFightStart", null));
		messageMap.put(PvpBeChoose, new MessageAndId("pvpBeChoose", null));
		messageMap.put(PvpUploadTeam, new MessageAndId("pvpUploadTeam", null));
		messageMap.put(UpdateChooseYourOperation, new MessageAndId("updateChooseYourOperation", null));
		messageMap.put(FamilyFightStart, new MessageAndId("familyFightStart", null));
		messageMap.put(Login, new MessageAndId("login", login.getDefaultInstance()));
		messageMap.put(CreateRole, new MessageAndId("createRole", createRole.getDefaultInstance()));
		messageMap.put(GetMailList, new MessageAndId("getMailList", getMailList.getDefaultInstance()));
		messageMap.put(ReadMailReward, new MessageAndId("readMailReward", readMailReward.getDefaultInstance()));
		messageMap.put(ReceiveMailReward, new MessageAndId("receiveMailReward", receiveMailReward.getDefaultInstance()));
		messageMap.put(DeleteMail, new MessageAndId("deleteMail", deleteMail.getDefaultInstance()));
		messageMap.put(GetRecommendFriends, new MessageAndId("getRecommendFriends", getRecommendFriends.getDefaultInstance()));
		messageMap.put(GetMyFriends, new MessageAndId("getMyFriends", getMyFriends.getDefaultInstance()));
		messageMap.put(GetFriendRequestList, new MessageAndId("getFriendRequestList", getFriendRequestList.getDefaultInstance()));
		messageMap.put(SendGift, new MessageAndId("sendGift", sendGift.getDefaultInstance()));
		messageMap.put(ReceiveGift, new MessageAndId("receiveGift", receiveGift.getDefaultInstance()));
		messageMap.put(FindUser, new MessageAndId("findUser", findUser.getDefaultInstance()));
		messageMap.put(RequestFriend, new MessageAndId("requestFriend", requestFriend.getDefaultInstance()));
		messageMap.put(AgreeFriendRequest, new MessageAndId("agreeFriendRequest", agreeFriendRequest.getDefaultInstance()));
		messageMap.put(RejectRequestFriend, new MessageAndId("rejectRequestFriend", rejectRequestFriend.getDefaultInstance()));
		messageMap.put(RemoveFriend, new MessageAndId("removeFriend", removeFriend.getDefaultInstance()));
		messageMap.put(GetBlackList, new MessageAndId("getBlackList", getBlackList.getDefaultInstance()));
		messageMap.put(AddBlack, new MessageAndId("addBlack", addBlack.getDefaultInstance()));
		messageMap.put(RemoveBlack, new MessageAndId("removeBlack", removeBlack.getDefaultInstance()));
		messageMap.put(HeartBeat, new MessageAndId("heartBeat", heartBeat.getDefaultInstance()));
		messageMap.put(F9, new MessageAndId("f9", f9.getDefaultInstance()));
		messageMap.put(Treasure, new MessageAndId("treasure", treasure.getDefaultInstance()));
		messageMap.put(HeroLevelUp, new MessageAndId("heroLevelUp", heroLevelUp.getDefaultInstance()));

	}

	public static class MessageAndId {
		public final String key;
		public final Message message;
		public Object object;
		public Method method;

		public MessageAndId(String key, Message message) {
			this.key = key;
			this.message = message;
		}

		public void setReflect(Object object, Method method) {
			this.object = object;
			this.method = method;
		}

		public Object getObject() {
			return object;
		}

		public Method getMethod() {
			return method;
		}

	}

}
