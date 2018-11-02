package com.jpkware.smng

import com.definitelyscala.phaser.{Game, Sprite, State}

class StatePreload(game: Game, options: Map[String,String]) extends State {

  var preloadBar: Sprite = _
  var ship: Sprite = _
  var step: Int = 10
  override def preload(): Unit = {

    ship = game.add.sprite(game.width / 4, game.height / 2, "ship-preload")
    ship.anchor.set(0, 0.5)
    preloadBar = game.add.sprite(game.width / 4 + 20, game.height / 2, "preloaderBar")
    preloadBar.anchor.set(1, 0.5)
    preloadBar.scale.set(1.25,1.25)
    game.load.setPreloadSprite(preloadBar)

    Scorebox.preloadResources(game)
    game.load.image("missile", "res/missile.png")
    game.load.image("flame", "res/flame.png")
    game.load.image("explo", "res/explo.png")
    game.load.bitmapFont("font", "res/font.png", "res/font.fnt")
    game.load.spritesheet("button", "res/button.png", 128, 128)
    game.load.atlasJSONHash("sprites", s"res/mainv2.png", s"res/mainv2.json")
    game.load.audio("sfx:levelclr", "res/levelclr.wav")
    game.load.audio("sfx:levelend", "res/levelend.wav")
    game.load.audio("sfx:zap", "res/zap.wav")
    game.load.audio("sfx:swip", "res/swip.wav")
    game.load.audio("sfx:explo", "res/explo.wav")
    game.load.audio("sfx:tinyexp", "res/tinyexp.wav")
    (0 to StarMinesNG.maxBackground).foreach(i => game.load.image(s"space$i", s"res/space$i.jpg"))
  }

  override def create(): Unit = {
  }

  override def update(): Unit = {
    ship.x += step
    preloadBar.x += step
    step += 2
    if (ship.x>game.width) {
      game.state.start("menu", args = null, clearCache = false, clearWorld = false)
      ship.destroy(true)
      preloadBar.destroy(true)
    }
  }

}